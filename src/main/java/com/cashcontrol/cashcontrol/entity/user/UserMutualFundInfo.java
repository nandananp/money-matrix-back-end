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
@Table(name = "usr_mf_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserMutualFundInfo extends DBTimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "mutual_fund_id")
    private UUID mutualFundId;

    @Column(name = "total_return")
    private Long totalReturn;

}
