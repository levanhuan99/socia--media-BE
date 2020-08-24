package com.huan.social.repositories;

import com.huan.social.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

}
