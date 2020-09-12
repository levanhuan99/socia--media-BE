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
            Optional<FriendRequest> friendRequest = this.friendRequestService.findFriendRequestByAcccountReciverAndAcccountSender(accountSender.get(), accountReciver.get());
            if (friendRequest.isPresent()){
                ResultSearch resultSearch = new ResultSearch();
                resultSearch.setId(accountList.get(a).getId());
                resultSearch.setAvatar(accountList.get(a).getAvatar());
                resultSearch.setCoverPhoto(accountList.get(a).getCoverPhoto());
                resultSearch.setEmail(accountList.get(a).getEmail());
                resultSearch.setNickName(accountList.get(a).getNickName());
                resultSearch.setPhoneNumber(accountList.get(a).getPhoneNumber());
                resultSearch.setFriendStatus(friendRequest.get().getFriendStatus());
                resultSearchList.add(resultSearch);
            }else {
                continue;
            }

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
            Optional<FriendRequest> friendRequest = this.friendRequestService.findFriendRequestByAcccountReciverAndAcccountSender(acountReciver.get(), accountSender.get());
            if (!friendRequest.isPresent()) {
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



