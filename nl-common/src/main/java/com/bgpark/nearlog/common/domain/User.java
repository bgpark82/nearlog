package com.bgpark.nearlog.common.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String token;

    public User(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }
}
