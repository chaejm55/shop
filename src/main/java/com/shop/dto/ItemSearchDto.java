package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemSearchDto {
    private String searchDateType; // all, 1d, 1w, 1m, 6m
    private ItemSellStatus searchSellStatus;
    private String searchBy;
    private String searchQuery = "";

    @Builder
    public ItemSearchDto(String searchDateType, ItemSellStatus searchSellStatus, String searchBy, String searchQuery) {
        this.searchDateType = searchDateType;
        this.searchSellStatus = searchSellStatus;
        this.searchBy = searchBy;
        this.searchQuery = searchQuery;
    }
}
