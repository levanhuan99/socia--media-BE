package com.huan.social.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    @Column(nullable = false, unique = true)
    private String email;// Dung email de login

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;


    private Date birthDay;

//    private boolean status; //Su dung status de admin co quyen blog tai khoan nay

    @Column(columnDefinition = "TEXT")
    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String coverPhoto;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
