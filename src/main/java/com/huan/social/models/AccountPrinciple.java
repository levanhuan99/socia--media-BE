package com.huan.social.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String email;
    private String password;

    private Collection<? extends  GrantedAuthority> roles;

    public AccountPrinciple(Long id, String email, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public static AccountPrinciple build( Account account){
        List<GrantedAuthority> authorities = account.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        return new AccountPrinciple(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountPrinciple that = (AccountPrinciple) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, roles);
    }
}
