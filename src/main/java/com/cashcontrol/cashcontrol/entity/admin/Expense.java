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
@Table(name = "adm_expns")
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "full_amount")
    private Long fullAmount;

    @Column(name = "emi")
    private Long emi;

    //admin adding expense would be there for every users when they start the game
    //it will get places in user liability info table and they have to make it zero




}