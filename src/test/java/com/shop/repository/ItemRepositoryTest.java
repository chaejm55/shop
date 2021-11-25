package com.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @PersistenceContext
    EntityManager em; // 영속성 컨텐츠 사용용

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

    public void createItemList2() {
        String itemName = "테스트 상품";
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10000;
        int stockNumber = 100;
        ItemSellStatus itemSellStatus = ItemSellStatus.SELL;

        for (int i = 1; i <= 5; i++) {
            Item savedItem = itemRepository.save(Item.builder()
                    .itemName(itemName + i)
                    .price(price + i)
                    .itemDetail(itemDetail + i)
                    .itemSellStatus(itemSellStatus)
                    .stockNumber(stockNumber)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now()).
                    build());
        }
        ItemSellStatus itemSellStatus2 = ItemSellStatus.SOLD_OUT;
        int stockNumber2 = 0;
        for (int i = 6; i <= 10; i++) {
            Item savedItem = itemRepository.save(Item.builder()
                    .itemName(itemName + i)
                    .price(price + i)
                    .itemDetail(itemDetail + i)
                    .itemSellStatus(itemSellStatus)
                    .stockNumber(stockNumber2)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now()).
                    build());
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNumTest() {
        this.createItemList();
        String itemName = "테스트 상품1";
        List<Item> itemList = itemRepository.findByItemName(itemName);
        for (Item item: itemList) {
            //System.out.println(item.toString());
            assertThat(item.getItemName()).isEqualTo(itemName);
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNumOrItemDetailTest() {
        this.createItemList();
        String itemName = "테스트 상품1";
        String itemDetail = "테스트 상품 상세 설명5";
        List<Item> itemList =
                itemRepository.findByItemNameOrItemDetail(itemName, itemDetail);
        for (Item item: itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        int price = 10005;
        List<Item> itemList = itemRepository.findByPriceLessThan(price);
        for (Item item: itemList) {
            //System.out.println(item.toString());
            assertThat(item.getPrice()).isLessThan(price);
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDescTest() {
        this.createItemList();
        int price = 10005;
        int decrease = 1;
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(price);
        for (Item item: itemList) {
            assertThat(item.getPrice()).isEqualTo(price - decrease);
            decrease++;
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest() {
        this.createItemList();
        String itemDetail = "테스트 상품 상세 설명";
        List<Item> itemList = itemRepository.findByItemDetail(itemDetail);
        for (Item item: itemList) {
            assertThat(item.getItemDetail()).contains(itemDetail);
        }
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품조회 테스트")
    public void findByItemDetailByNativeTest() {
        this.createItemList();
        String itemDetail = "테스트 상품 상세 설명";
        List<Item> itemList = itemRepository.findByItemDetailByNative(itemDetail);
        for (Item item: itemList) {
            assertThat(item.getItemDetail()).contains(itemDetail);
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트1")
    public void queryDslTest1() {
        this.createItemList();
        String itemDetail = "테스트 상품 상세 설명";
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + itemDetail + "%"))
                .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch(); // 쿼리 결과를 리스트로 반환

        for (Item item: itemList) {
            assertThat(item.getItemDetail()).contains(itemDetail);
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트2")
    public void queryDslTest2() {
        this.createItemList2();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10003;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
        booleanBuilder.and(item.price.gt(price));
        System.out.println(ItemSellStatus.SELL);
        if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + itemPagingResult.getTotalElements());
        List<Item> resultItemList = itemPagingResult.getContent();
        for (Item resultItem: resultItemList) {
            assertThat(resultItem.getItemDetail()).contains(itemDetail);
        }
    }
}