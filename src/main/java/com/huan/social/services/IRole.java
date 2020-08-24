package com.huan.social.services;

import com.huan.social.models.Role;

public interface IRole {
    Iterable<Role> findAll();

    Role save(Role role);
    Role findByName(String name);
}
