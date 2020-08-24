package com.huan.social.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    public Role(Long id, String role_admin) {
        this.id = id;
        this.name = role_admin;
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role() {

    }

}
