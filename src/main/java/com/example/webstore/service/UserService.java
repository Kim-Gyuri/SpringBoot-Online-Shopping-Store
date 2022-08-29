package com.example.webstore.service;

import com.example.webstore.domain.Item;
import com.example.webstore.domain.ItemImg;
import com.example.webstore.domain.User;
import com.example.webstore.dto.LoginFormDto;
import com.example.webstore.dto.UserFormDto;
import com.example.webstore.dto.UserMainItemDto;
import com.example.webstore.repository.ItemImgRepository;
import com.example.webstore.repository.ItemRepository;
import com.example.webstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ItemImgRepository imgRepository;

    public Long signUp(UserFormDto userFormDto) {
        return userRepository.save(userFormDto.UserEntity()).getId();
    }

    public User signIn(LoginFormDto loginFormDto) {
            Optional<User> byLoginId = userRepository.findByLoginId(loginFormDto.getLoginId());
            return byLoginId.filter(m -> m.getPassword().equals(loginFormDto.getPassword()))
                    .orElse(null);
    }

    @Transactional(readOnly = true)
    public User findOne(String loginId) {
        Optional<User> byLoginId = userRepository.findByLoginId(loginId);
        return byLoginId.orElseThrow(() -> new IllegalArgumentException("Invalid board id= " + loginId));
    }


    @Transactional(readOnly = true)
    public List<UserMainItemDto> findAllByUser(User user) {
        List<UserMainItemDto> userMainItemDtos = itemRepository.sortByUser();
        List<Item> getItemList = itemRepository.findAllByUser(user);

        List<UserMainItemDto> result = new ArrayList<>();
        for (Item item : getItemList) {
            for (UserMainItemDto userMainItemDto : userMainItemDtos) {
                if (item.getId() == userMainItemDto.getItemId()) {
                    result.add(userMainItemDto);
                }
            }
        }
        return result;
    }

    @Transactional
    public void deleteV3(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid board id= " + itemId));
        List<ItemImg> itemImgList = imgRepository.findAllByItem_id(itemId);
        itemRepository.delete(item);
        for (ItemImg itemImg : itemImgList) {
            imgRepository.delete(itemImg);
        }

    }



}
