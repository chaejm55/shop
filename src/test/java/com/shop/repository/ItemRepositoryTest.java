package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    public void cleanup() {
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        String itemName = "테스트 상품";
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10000;
        int stockNumber = 100;
        ItemSellStatus itemSellStatus = ItemSellStatus.SELL;

        Item savedItem = itemRepository.save(Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDetail(itemDetail)
                .itemSellStatus(itemSellStatus)
                .stockNumber(stockNumber)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now()).
                build());

        // System.out.println(savedItem.toString());

        // given
        List<Item> itemList = itemRepository.findAll();

        // when
        Item item = itemList.get(0);
        assertThat(item.getItemName()).isEqualTo(itemName);
        assertThat(item.getItemDetail()).isEqualTo(itemDetail);
        assertThat(item.getPrice()).isEqualTo(price);
        assertThat(item.getStockNumber()).isEqualTo(stockNumber);
        assertThat(item.getItemSellStatus()).isEqualTo(itemSellStatus);
    }

    public void createItemList() {
        String itemName = "테스트 상품";
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10000;
        int stockNumber = 100;
        ItemSellStatus itemSellStatus = ItemSellStatus.SELL;

        for (int i = 1; i <= 10; i++) {
            Item savedItem = itemRepository.save(Item.builder()
                    .itemName(itemName + i)
                    .price(price + i)
                    .itemDetail(itemDetail + i)
                    .itemSellStatus(itemSellStatus)
                    .stockNumber(stockNumber)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now()).
                    build());
            // System.out.println(savedItem.toString());
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNumTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemName("테스트 상품1");
        for (Item item: itemList) {
            //System.out.println(item.toString());
            assertThat(item.getItemName()).isEqualTo("테스트 상품1");
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNumOrItemDetailTest() {
        this.createItemList();
        List<Item> itemList =
                itemRepository.findByItemNameOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
        for (Item item: itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item: itemList) {
            //System.out.println(item.toString());
            assertThat(item.getPrice()).isLessThan(10005);
        }
    }
}