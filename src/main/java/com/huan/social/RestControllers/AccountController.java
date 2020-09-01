package com.huan.social.RestControllers;

import com.huan.social.models.Account;
import com.huan.social.services.impl.AccountService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user/")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("{id}/detail")
    public ResponseEntity<Account> getUser(@PathVariable Long id){
        Optional<Account> account=accountService.findById(id);
        if (account.isPresent()){
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}/edit")
    public  ResponseEntity<Account> editAccount(@PathVariable Long id,@RequestBody Account account){
        Optional<Account> currentAccount=accountService.findById(id);
        if (!currentAccount.isPresent()){
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        currentAccount.get().setNickName(account.getNickName());
        currentAccount.get().setPhoneNumber(account.getPhoneNumber());
        currentAccount.get().setEmail(account.getEmail());
        accountService.save(currentAccount.get());

        return new ResponseEntity<>(currentAccount.get(),HttpStatus.OK);
    }

}
