package com.example.webstore.service;

import com.example.webstore.domain.Item;
import com.example.webstore.domain.ItemImg;
import com.example.webstore.domain.ItemSellStatus;
import com.example.webstore.domain.User;
import com.example.webstore.dto.ItemFormDto;
import com.example.webstore.dto.UserFormDto;
import com.example.webstore.repository.ItemImgRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemImgRepository imgRepository;
    @Autowired UserService userService;

    List<MultipartFile> createMultipartFiles() throws Exception{
        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0;i<5;i++){ // 상품 이미지 경로 + 이미지 이름 저장해서 add
            String path = "C:/shop/item/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile =
                    new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }

    public User createUserTest() {
        UserFormDto dto = new UserFormDto();
        dto.setLoginId("nana20");
        dto.setPassword("1234@");
        dto.setName("faker");
        dto.setEmail("karis99@naver.com");
        dto.setCity("서울");
        dto.setStreet("마포구 합정동");
        dto.setZipcode("1120");

        Long savedId = userService.signUp(dto);
        return userService.findOne(dto.getLoginId());
    }

    @Test
    @DisplayName("상품 등록 테스트")
    void saveItem() throws Exception {
        List<MultipartFile> multipartFiles = createMultipartFiles();
        User user = createUserTest();

        ItemFormDto dto = new ItemFormDto();
        dto.setItemName("테스트 상품명");
        dto.setCategoryType("BOOK");
        dto.setItemType("최상");
        dto.setPrice(10000);
        dto.setQuantity(100);
        dto.setStatus(ItemSellStatus.SELL);

        Long itemId = itemService.saveItem(user, dto, multipartFiles);
        List<ItemImg> itemImgList = imgRepository.findAllByItem_id(itemId);

        Item item = itemService.findById(itemId);

        assertEquals(dto.getItemName(), item.getItemName());
        assertEquals(dto.getCategoryType(), item.getCategoryType());
        assertEquals(multipartFiles.get(0).getOriginalFilename(), itemImgList.get(0).getOriginImgName());


    }

}