package com.cashcontrol.cashcontrol.entity.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Setter
@Getter
@Table(name = "adm_stk")
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "minimum_price")
    private Long minimumPrice;

    @Column(name = "maximum_price")
    private Long maximumPrice;

    @Column(name = "current_price")
    private Long currentPrice;



}