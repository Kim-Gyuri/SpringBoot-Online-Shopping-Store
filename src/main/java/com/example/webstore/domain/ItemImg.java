package com.example.webstore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemImg {

    @Id @GeneratedValue
    @Column(name = "item_img_id")
    private Long id;

    private String originImgName; // 파일이름
    private String imgName;  //저장된 uuid 이름
    private String savePath; //파일저장경로
    private String reImgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder(builderMethodName = "imgBuilder")
    public ItemImg(String originImgName, String imgName, String savePath, String reImgYn, Item item) {
        this.originImgName = originImgName;
        this.imgName = imgName;
        this.savePath = savePath;
        this.reImgYn = reImgYn;
        this.item = item;
    }

    @Override
    public String toString() {
        return "ItemImg Info {" + " ImgName =" + originImgName + ", storeFileName =" + imgName + ", savePath =" + savePath + '}';
    }
}