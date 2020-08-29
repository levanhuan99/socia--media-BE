package com.huan.social.RestControllers;


import com.huan.social.models.Account;
import com.huan.social.models.JwtResponse;
import com.huan.social.services.JwtService;
import com.huan.social.services.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping()
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account currentAccount = accountService.findByEmail(account.getEmail()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentAccount.getId(), userDetails.getUsername(), currentAccount.getNickName(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        Iterable<Account> listAccount =  this.accountService.findAll();
        for (Account acc:listAccount) {
            if (acc.getEmail().equals(account.getEmail())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        this.accountService.save(account);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }
}
