package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByItemName(String itemName); // 상품명으로 찾기

    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail); // 상품명이나 상품 상세 설명으로 찾기

    List<Item> findByPriceLessThan(Integer price); // 주어진 가격 미만의 가격으로 찾기

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price); // 주어진 가격 미만의 가격에서 내림차순 정렬하여 찾기

    // JPQL 쿼리문으로 쿼리 작성, Item 엔티티로부터 데이터를 select 함
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); // %:itemDetail% 에 넣을 값

    // native Query(SQL) 쿼리문으로 쿼리 작성, Item 엔티티로부터 데이터를 select 함
    @Query(value = "select * from Item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail); // %:itemDetail% 에 넣을 값
}
