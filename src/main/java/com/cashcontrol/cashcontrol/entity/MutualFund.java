package com.cashcontrol.cashcontrol.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Setter
@Getter
@Table(name = "adm_mf")
@AllArgsConstructor
@NoArgsConstructor
public class MutualFund {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "minimum_amount")
    private Long minimumAmount;



}