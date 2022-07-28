package com.example.webstore.service;

import com.example.webstore.controller.dto.ItemSearchCondition;
import com.example.webstore.domain.Item;
import com.example.webstore.domain.ItemImg;
import com.example.webstore.domain.User;
import com.example.webstore.dto.ItemFormDto;
import com.example.webstore.dto.ItemImgDto;
import com.example.webstore.dto.ItemInfo;
import com.example.webstore.dto.MainItemDto;
import com.example.webstore.repository.ItemImgRepository;
import com.example.webstore.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService imgService;
    private final ItemImgRepository imgRepository;

    public Long saveItem(User user, ItemFormDto itemFormDto, List<MultipartFile> multipartFileList) throws IOException {
        Item item = itemFormDto.toEntity();
        item.setUpUser(user);
        Long id = itemRepository.save(item).getId();

        //대표 이미지 구별
        for (int i=0; i<multipartFileList.size(); i++) {
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setItem(item);

            if (i==0)
                itemInfo.setRepImgYn("Y");
            else
                itemInfo.setRepImgYn("N");

            imgService.saveItemImg(itemInfo, multipartFileList.get(i));
        }

        return id;
    }

    @Transactional(readOnly = true)
    public Item findByItemName(String itemName) {
         return itemRepository.findByItemName(itemName);
    }

    @Transactional(readOnly = true)
    public Item findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board id= " + id ));
        return item;
    }



    @Transactional(readOnly = true)
    public ItemFormDto getItemDetail(Long itemId) {
        List<ItemImg> itemImgList = imgRepository.findAllByItem_id(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();

        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        //item(Entity) -> itemformDto(DTO) --> itemimgdtoList (setting)
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board id= " + itemId ));
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }

    @Transactional(readOnly = true)
    public Page<MainItemDto> searchPageSort(ItemSearchCondition condition, Pageable pageable) {
        return itemRepository.searchPageSort(condition, pageable);
    }


    @Transactional(readOnly = true)
    public Page<MainItemDto> categoryPageSort(String code, ItemSearchCondition condition, Pageable pageable) {
        return itemRepository.categoryPageSort(code, condition,pageable);
    }


    public void delete(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. "));
        itemRepository.delete(item);
    }


}
