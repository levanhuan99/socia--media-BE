package com.huan.social.services;

import com.huan.social.models.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IAccount extends UserDetailsService {
    Iterable<Account> findAll();

    Optional<Account> findByEmail(String email);
    Account save(Account account);

    Optional<Account> findById(Long id);

    List<Account> findAccountByNickName(String userName);
}
