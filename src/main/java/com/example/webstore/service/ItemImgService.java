package com.example.webstore.service;

import com.example.webstore.domain.ItemImg;
import com.example.webstore.dto.FileInfo;
import com.example.webstore.dto.ItemInfo;
import com.example.webstore.file.FileService;
import com.example.webstore.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemImgService {

    private final FileService fileService;
    private final ItemImgRepository imgRepository;

    public Long saveItemImg(ItemInfo itemInfo, MultipartFile multipartFile) throws IOException {
        FileInfo fileInfo = fileService.storeFile(multipartFile);

        ItemImg imgEntity = ItemImg.imgBuilder()
                .originImgName(fileInfo.getOriginImgName())
                .imgName(fileInfo.getImgName())
                .savePath(fileInfo.getSavePath())
                .reImgYn(itemInfo.getRepImgYn())
                .item(itemInfo.getItem())
                .build();

        ItemImg saved = imgRepository.save(imgEntity);
        return saved.getId();

    }
}
