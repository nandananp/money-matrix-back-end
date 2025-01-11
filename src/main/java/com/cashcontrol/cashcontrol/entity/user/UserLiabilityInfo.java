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
@Table(name = "usr_lblty")
@AllArgsConstructor
@NoArgsConstructor
public class UserLiabilityInfo extends DBTimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "lblty_name")
    private String liabilityName;

    @Column(name = "emi")
    private String emi;

}
