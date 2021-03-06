package com.huan.social.services;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;

import java.util.List;
import java.util.Optional;

public interface IFriendRequest {
    void save(FriendRequest request);

    FriendRequest findFrienRequestByAccountSenderAndAccountReceiver1(Integer accountSenderId, Integer accountReciverId);
    void delete(FriendRequest friendRequest);
    List<FriendRequest> findAllFriend(String status, Integer accountReceiverId, Integer accountSenderId);

    List<FriendRequest> findRequests(String status,Integer receiverId);
    FriendRequest findFriendRequestByAccountSenderAndAccountReceiver2(Integer acccountReciverId, Integer acccountSenderId);

    FriendRequest findFriend(Integer id1, Integer id2,Integer if3, Integer id4);

}
