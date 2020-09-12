package com.huan.social.services;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;

import java.util.List;
import java.util.Optional;

public interface IFriendRequest {
    void save(FriendRequest request);
    Optional<FriendRequest> findFriendRequestByAcccountReciverAndAcccountSender(Account accountReciver, Account accountSender);
    Optional<FriendRequest> findByAcccountReciverAndAcccountSender(Account accountReciver , Account accountSender);
    List<FriendRequest> findAllByAcccountSender(Account account);
    List<FriendRequest> findAllByAcccountSenderAndAcccountReciver(Account accountReciver , Account accountSender);

}
