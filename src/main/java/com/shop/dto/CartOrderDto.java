package com.shop.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CartOrderDto {
    private Long cartItemId;
    private List<CartOrderDto> cartOrderDtoList;

    @Builder
    public CartOrderDto(Long cartItemId, List<CartOrderDto> cartOrderDtoList) {
        this.cartItemId = cartItemId;
        this.cartOrderDtoList = cartOrderDtoList;
    }
}
