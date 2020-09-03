package com.huan.social.RestControllers;


import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
import com.huan.social.services.IAccount;
import com.huan.social.services.impl.AccountService;
import com.huan.social.services.impl.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/friends/")
public class FriendFinderController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private FriendRequestService friendRequestService;

    @GetMapping("search")
    public ResponseEntity<List<Account>> getSearchResult( String q) {

        List<Account> accountList = accountService.findAccountByNickName(q);
        if (accountList.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(accountList, HttpStatus.OK);
        }
    }
//
    @PostMapping("sendRequest")
    public ResponseEntity<?> sendRequest( @RequestBody Long senderId, @RequestBody Long reciverId){
        Optional<Account> reciverAccount=accountService.findById(senderId);
        Optional<Account> senderAccount=accountService.findById(reciverId);
        if (reciverAccount.isPresent()&&senderAccount.isPresent()){
            FriendRequest request=new FriendRequest();

            request.setAcccountSender(senderAccount);


            request.setAccountReciver(reciverAccount);
            request.setFriendStatus("Pending");

            friendRequestService.save(request);
            return ResponseEntity<>(HttpStatus.OK)
        }
        else {
            ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
