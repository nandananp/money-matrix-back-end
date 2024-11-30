package com.cashcontrol.cashcontrol.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;


@Entity
@Setter
@Getter
@Table(name = "usr")
@AllArgsConstructor
@NoArgsConstructor
public class User extends DBTimeStamp{


    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;
}
