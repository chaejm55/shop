package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

// lombok annotation은 바깥쪽에
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 상품 코드

    @Column(nullable = false, length = 50)
    private String itemName; // 상품명

    @Column(name = "price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
    private LocalDateTime regTime; // 등록 시간
    private LocalDateTime updateTime; // 수정시간
    
    // setter 대신 빌더 패턴으로 생성자 구현
    @Builder
    public Item(Long id, String itemName, int price, int stockNumber,
                String itemDetail, ItemSellStatus itemSellStatus,
                LocalDateTime regTime, LocalDateTime updateTime) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
        this.regTime = regTime;
        this.updateTime = updateTime;
    }
}
