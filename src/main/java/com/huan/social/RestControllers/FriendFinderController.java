package com.huan.social.RestControllers;


import com.huan.social.models.Account;
import com.huan.social.services.IAccount;
import com.huan.social.services.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/friends/")
public class FriendFinderController {

    @Autowired
    private AccountService accountService;

    @GetMapping("search")
    public ResponseEntity<List<Account>> getSearchResult(@RequestBody String userName){

       List<Account> accountList= accountService.findAccountByNickName(userName);
       if (accountList.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       else {
           return new ResponseEntity<>(accountList,HttpStatus.OK);
       }
    }
}
