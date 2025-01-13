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
@Table(name = "usr_game_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserGameInfo extends DBTimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "job_id")
    private UUID jobId;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "passive_income")
    private Long passiveIncome;

    @Column(name = "status")
    private String status;

    @Column(name = "savings")
    private Long savings;

    @Column(name = "level")
    private String level;




}
