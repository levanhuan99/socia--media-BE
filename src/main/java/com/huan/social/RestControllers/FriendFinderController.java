package com.huan.social.RestControllers;


import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
import com.huan.social.models.responseApi.ResultSearch;
import com.huan.social.services.impl.AccountService;
import com.huan.social.services.impl.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    //need to have id of sender to find list Account for designing API when return
    @GetMapping("search")
    public ResponseEntity<List<ResultSearch>> getSearchResult(String q, Long senderId) {
        Optional<Account> accountSender = this.accountService.findById(senderId);
        List<Account> accountList = accountService.findAccountByNickName(q);
        List<ResultSearch> resultSearchList = new ArrayList<>();
        for (int a = 0; a < accountList.size(); a++) {
            Optional<Account> accountReciver = this.accountService.findById(accountList.get(a).getId());
            FriendRequest friendRequest = this.friendRequestService.findFriendRequestByAcccountReciverAndAcccountSender(accountSender.get(), accountReciver.get());
            resultSearchList.get(a).setId(accountList.get(a).getId());
            resultSearchList.get(a).setAvatar(accountList.get(a).getAvatar());
            resultSearchList.get(a).setCoverPhoto(accountList.get(a).getCoverPhoto());
            resultSearchList.get(a).setEmail(accountList.get(a).getEmail());
            resultSearchList.get(a).setNickName(accountList.get(a).getNickName());
            resultSearchList.get(a).setPhoneNumber(accountList.get(a).getPhoneNumber());
            resultSearchList.get(a).setFriendStatus(friendRequest.getFriendStatus());
        }
        if (resultSearchList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(resultSearchList, HttpStatus.OK);
        }
    }

    @PostMapping("sendRequest")
    public ResponseEntity<?> sendRequest(Long senderId, Long reciverId) {
        Optional<Account> acountReciver = accountService.findById(senderId);
        Optional<Account> accountSender = accountService.findById(reciverId);

        if (acountReciver.isPresent() && accountSender.isPresent()) {
            FriendRequest friendRequest = this.friendRequestService.findFriendRequestByAcccountReciverAndAcccountSender(acountReciver.get(), accountSender.get());
            if (friendRequest != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            FriendRequest request = new FriendRequest();
            request.setAcccountReciver(acountReciver.get());
            request.setAcccountSender(accountSender.get());
            request.setFriendStatus("Pending");
            friendRequestService.save(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("listRequest")
//    public ResponseEntity<?> getListRequest(){
//
//        return new ResponseEntity<?>();
//    }
//
}



