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
    public FriendRequest findFrienRequestByAccount(Integer accountSenderId, Integer accountReciverId) {
        return this.friendRequestRepository.findFrienRequestByAccount(accountSenderId,accountReciverId);
    }

}
