package com.cashcontrol.cashcontrol.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "usr_stk_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserStockInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "stock_id")
    private UUID stockId;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "stock_count")
    private Long stockCount;

    @Column(name = "current_price")
    private Long currentPrice;

}
