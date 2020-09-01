package com.huan.social.repositories;

import com.huan.social.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface AccountRepositories extends JpaRepository<Account,Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findById(Long id);

    List<Account> findAccountsByNickNameContaining(String userName);
}
