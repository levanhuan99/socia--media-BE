package com.huan.social.services.impl;

import com.huan.social.models.Account;
import com.huan.social.models.AccountPrinciple;
import com.huan.social.models.Role;
import com.huan.social.repositories.AccountRepositories;
import com.huan.social.services.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService implements IAccount {

    @Autowired
    private AccountRepositories accountRepository;

    @Autowired
    private RoleService roleService;



    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByEmail(username);
        if (!accountOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return AccountPrinciple.build(accountOptional.get());
    }

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }


    @Override
    public Account save(Account account) {
        if (account.getRoles() == null) {
            Role role = roleService.findByName("ROLE_USER");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            account.setRoles(roles);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> findAccountByNickName(String userName) {
        return accountRepository.findAccountsByNickNameContaining(userName);
    }
    Account getAll(){
        return new Account();
    }
}
