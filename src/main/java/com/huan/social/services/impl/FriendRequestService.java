package com.huan.social.services.impl;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
import com.huan.social.repositories.FriendRequestRepository;
import com.huan.social.services.IFriendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestService implements IFriendRequest {
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Override
    public void save(FriendRequest request) {
        friendRequestRepository.save(request);
    }

    @Override
    public Optional<FriendRequest> findFriendRequestByAcccountReciverAndAcccountSender(Account accountReciver, Account accountSender) {
        return this.friendRequestRepository.findFriendRequestByAcccountReciverAndAcccountSender(accountReciver, accountSender);
    }

    @Override
    public Optional<FriendRequest> findByAcccountReciverAndAcccountSender(Account accountReciver, Account accountSender) {
        return this.friendRequestRepository.findByAcccountReciverAndAcccountSender(accountReciver,accountSender);
    }

    @Override
    public List<FriendRequest> findAllByAcccountSender(Account account) {
        return this.friendRequestRepository.findAllByAcccountSender(account);
    }

    @Override
    public List<FriendRequest> findAllByAcccountSenderAndAcccountReciver(Account accountReciver, Account accountSender) {
        return this.friendRequestRepository.findAllByAcccountSenderAndAcccountReciver(accountReciver,accountSender);
    }






}
