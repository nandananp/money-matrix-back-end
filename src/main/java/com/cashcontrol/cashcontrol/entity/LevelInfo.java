package com.cashcontrol.cashcontrol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "adm_lvl_info")
@AllArgsConstructor
@NoArgsConstructor
public class LevelInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "level_number")
    private String levelNumber;

    @Column(name = "description")
    private String description;



}
