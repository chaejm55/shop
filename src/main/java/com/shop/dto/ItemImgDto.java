package com.shop.dto;

import com.shop.entity.ItemImg;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemImgDto {
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    @Builder
    public ItemImgDto(Long id, String imgName, String oriImgName, String imgUrl, String repImgYn) {
        this.id = id;
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }

    public ItemImg toEntity() {
        return ItemImg.builder()
                .id(id)
                .imgName(imgName)
                .oriImgName(oriImgName)
                .imgUrl(imgUrl)
                .repimgYn(repImgYn)
                .build();
    }
}
