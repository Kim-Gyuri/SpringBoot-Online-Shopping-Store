package com.example.webstore.dto;

import lombok.Data;

@Data
public class FileInfo {

    private String imgName;
    private String originImgName;
    private String savePath;

    public void updateItmImg(String originImgName, String imgName, String savePath) {
        this.originImgName = originImgName;
        this.imgName = imgName;
        this.savePath = savePath;
    }
}
