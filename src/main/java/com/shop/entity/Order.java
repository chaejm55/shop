package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // orderItem의 order 필드에 의해 관리됨, 모든 영속성 상태 전이
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(Long id, Member member, LocalDateTime orderDate, OrderStatus orderStatus) {
        this.id = id;
        this.member = member;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
