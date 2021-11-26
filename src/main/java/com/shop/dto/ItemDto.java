package com.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String itemName;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    @Builder
    public ItemDto(Long id, String itemName, Integer price, String itemDetail, String sellStatCd,
                   LocalDateTime regTime, LocalDateTime updateTime) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.sellStatCd = sellStatCd;
        this.regTime = regTime;
        this.updateTime = updateTime;
    }
}
