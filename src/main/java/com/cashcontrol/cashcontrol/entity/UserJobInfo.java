package com.cashcontrol.cashcontrol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "usr_job_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserJobInfo {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "job_id")
    private UUID jobId;

    @Column(name = "passive_income")
    private Long passiveIncome;

    @Column(name = "status")
    private String status;


}
