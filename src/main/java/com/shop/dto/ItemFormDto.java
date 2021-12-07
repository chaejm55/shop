package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "설명은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    @Builder
    public ItemFormDto(Long id, String itemName, Integer price, String itemDetail, Integer stockNumber,
                       ItemSellStatus itemSellStatus, List<ItemImgDto> itemImgDtoList, List<Long> itemImgIds) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
        this.itemImgDtoList = itemImgDtoList;
        this.itemImgIds = itemImgIds;
    }

    public Item toEntity() {
        return Item.builder()
                .id(this.id)
                .itemName(this.itemName)
                .price(this.price)
                .itemDetail(this.itemDetail)
                .stockNumber(this.stockNumber)
                .itemSellStatus(this.itemSellStatus)
                .build();
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemFormDto of(Item item){
        return modelMapper.map(item,ItemFormDto.class);
    }
}
