package com.huan.social.RestControllers;


import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
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


    //need to have id of sender to find list Account for designing API when return
    @GetMapping("search")
    public ResponseEntity<List<Account>> getSearchResult(String q,) {

        List<Account> accountList = accountService.findAccountByNickName(q);
        List<FriendRequest> friendRequestList=null;
        for (int a = 0; a < accountList.size(); a++) {
//            accountList.get(a).getId()
            List<FriendRequest> list=this.friendRequestService.findAllByAcccountSender();
        }
        if (accountList.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(accountList, HttpStatus.OK);
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



