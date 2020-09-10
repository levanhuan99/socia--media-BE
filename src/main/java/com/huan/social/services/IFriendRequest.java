package com.huan.social.services;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;

import java.util.List;

public interface IFriendRequest {
    void save(FriendRequest request);
    FriendRequest findFriendRequestByAcccountReciverAndAcccountSender(Account accountReciver,Account accountSender);
    List<FriendRequest> findAllByAcccountSender(Account account);
}
