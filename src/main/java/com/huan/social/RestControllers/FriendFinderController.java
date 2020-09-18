package com.huan.social.RestControllers;


import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
import com.huan.social.models.responseApi.ResponseFriend;
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
    public ResponseEntity<List<ResponseFriend>> getSearchResult(String q, Long senderId) {
        Integer SenderId = Math.toIntExact(senderId);

        List<Account> accountList = accountService.findAccountByNickName(q);

        List<ResponseFriend> responseFriendList = new ArrayList<>();

        for (int a = 0; a < accountList.size(); a++) {

            Integer ReciverId = Math.toIntExact(accountList.get(a).getId());

            FriendRequest friendRequest1 = this.friendRequestService.findFrienRequestByAccountSenderandAccountReceiver(ReciverId, SenderId);
            ResponseFriend responseFriend = new ResponseFriend();
            responseFriend.setId(accountList.get(a).getId());
            responseFriend.setAvatar(accountList.get(a).getAvatar());
            responseFriend.setCoverPhoto(accountList.get(a).getCoverPhoto());
            responseFriend.setEmail(accountList.get(a).getEmail());
            responseFriend.setNickName(accountList.get(a).getNickName());
            responseFriend.setPhoneNumber(accountList.get(a).getPhoneNumber());
            if (friendRequest1 != null) {

                responseFriend.setFriendStatus(friendRequest1.getFriendStatus());
            } else {
                responseFriend.setFriendStatus("No");
            }
            responseFriendList.add(responseFriend);
        }

        if (responseFriendList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity<>(responseFriendList, HttpStatus.OK);
        }
    }


    @PostMapping("sendRequest")
    public ResponseEntity<?> sendRequest(Long senderId, Long reciverId) {

        Optional<Account> acountReciver = accountService.findById(senderId);
        Optional<Account> accountSender = accountService.findById(reciverId);
        Integer SenderId = Math.toIntExact(senderId);
        Integer ReciverId = Math.toIntExact(reciverId);
        if (acountReciver.isPresent() && accountSender.isPresent()) {
            FriendRequest friendRequest = this.friendRequestService.findFrienRequestByAccountSenderandAccountReceiver(SenderId, ReciverId);
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

    @GetMapping("request-list")
    public ResponseEntity<List<ResponseFriend>> getRequestList(Long id) {
        Optional<Account> account = accountService.findById(id);
        Integer Id = Math.toIntExact(id);
        List<ResponseFriend> responseFriends = new ArrayList<>();
        if (account.isPresent()) {
            List<FriendRequest> friendRequestList = this.friendRequestService.findRequests( "Pending",Id);
            if (!friendRequestList.isEmpty()) {
                for (int i = 0; i < friendRequestList.size(); i++) {
                    ResponseFriend responseFriend = new ResponseFriend();
                    Optional<Account> friend = accountService.findById(Long.valueOf(friendRequestList.get(i).getAcccountSenderId()));
                    responseFriend.setId(friend.get().getId());
                    responseFriend.setAvatar(friend.get().getAvatar());
                    responseFriend.setCoverPhoto(friend.get().getCoverPhoto());
                    responseFriend.setEmail(friend.get().getEmail());
                    responseFriend.setNickName(friend.get().getNickName());
                    responseFriend.setPhoneNumber(friend.get().getPhoneNumber());
                    responseFriend.setFriendStatus(friendRequestList.get(i).getFriendStatus());
                    responseFriends.add(responseFriend);
                }
                return new ResponseEntity<>(responseFriends, HttpStatus.OK);

            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("unfriend")
    public ResponseEntity<?> unfriend(Long senderId, Long reciverId) {

        Optional<Account> acountReciver = accountService.findById(senderId);
        Optional<Account> accountSender = accountService.findById(reciverId);
        Integer SenderId = Math.toIntExact(senderId);
        Integer ReciverId = Math.toIntExact(reciverId);
        if (acountReciver.isPresent() && accountSender.isPresent()) {
            FriendRequest friendRequest = this.friendRequestService.findFrienRequestByAccountSenderandAccountReceiver(SenderId, ReciverId);
            if (friendRequest != null) {
                this.friendRequestService.delete(friendRequest);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseFriend>> getFriendList(Long id) {
        Optional<Account> acount = accountService.findById(id);
        Integer Id = Math.toIntExact(id);
        List<ResponseFriend> responseFriends = new ArrayList<>();
        if (acount.isPresent()) {
            List<FriendRequest> friendRequestList = this.friendRequestService.findAllFriend("Yes", Id, Id);
            if (!friendRequestList.isEmpty()) {
                for (int i = 0; i < friendRequestList.size(); i++) {
                    ResponseFriend responseFriend = new ResponseFriend();
                    Long friendId = null;
                    if (Id != friendRequestList.get(i).getAcccountSenderId()) {
                        friendId = Long.valueOf(friendRequestList.get(i).getAcccountSenderId());
                    } else {
                        friendId = Long.valueOf(friendRequestList.get(i).getAcccountReciverId());
                    }
                    Optional<Account> friend = accountService.findById(friendId);
                    responseFriend.setId(friend.get().getId());
                    responseFriend.setAvatar(friend.get().getAvatar());
                    responseFriend.setCoverPhoto(friend.get().getCoverPhoto());
                    responseFriend.setEmail(friend.get().getEmail());
                    responseFriend.setNickName(friend.get().getNickName());
                    responseFriend.setPhoneNumber(friend.get().getPhoneNumber());
                    responseFriend.setFriendStatus(friendRequestList.get(i).getFriendStatus());
                    responseFriends.add(responseFriend);
                }
                return new ResponseEntity<>(responseFriends, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("confirm")
    public ResponseEntity<?> confirmRequest(Long receiverId,Long senderId){
        Optional<Account> accountReceiver = accountService.findById(receiverId);
        Optional<Account> accountSender = accountService.findById(senderId);
        Integer SenderId = Math.toIntExact(senderId);
        Integer ReceiverId = Math.toIntExact(receiverId);
        if (accountReceiver.isPresent() && accountSender.isPresent()){
            FriendRequest friendRequest = this.friendRequestService.findFrienRequestByAccountSenderandAccountReceiver(ReceiverId,SenderId);
            if (friendRequest!=null){
                friendRequest.setFriendStatus("Yes");
                this.friendRequestService.save(friendRequest);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
