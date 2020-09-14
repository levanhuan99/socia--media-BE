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
        Integer SenderId = Math.toIntExact(senderId);

        List<Account> accountList = accountService.findAccountByNickName(q);

        List<ResultSearch> resultSearchList = new ArrayList<>();

        for (int a = 0; a < accountList.size(); a++) {

            Integer ReciverId = Math.toIntExact(accountList.get(a).getId());

            FriendRequest friendRequest1 = this.friendRequestService.findFrienRequestByAccount(ReciverId, SenderId);
            ResultSearch resultSearch = new ResultSearch();
            resultSearch.setId(accountList.get(a).getId());
            resultSearch.setAvatar(accountList.get(a).getAvatar());
            resultSearch.setCoverPhoto(accountList.get(a).getCoverPhoto());
            resultSearch.setEmail(accountList.get(a).getEmail());
            resultSearch.setNickName(accountList.get(a).getNickName());
            resultSearch.setPhoneNumber(accountList.get(a).getPhoneNumber());
            if (friendRequest1 != null) {

                resultSearch.setFriendStatus(friendRequest1.getFriendStatus());
            }else {
                resultSearch.setFriendStatus("No");
            }
            resultSearchList.add(resultSearch);


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
        Integer SenderId = Math.toIntExact(senderId);
        Integer ReciverId = Math.toIntExact(reciverId);
        if (acountReciver.isPresent() && accountSender.isPresent()) {
            FriendRequest friendRequest = this.friendRequestService.findFrienRequestByAccount(SenderId, ReciverId);
            if (friendRequest != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            FriendRequest request = new FriendRequest();
            request.setAcccountReciverId(Math.toIntExact(acountReciver.get().getId()));
            request.setAcccountSenderId(Math.toIntExact(accountSender.get().getId()));
            request.setFriendStatus("Pending");
            friendRequestService.save(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}



