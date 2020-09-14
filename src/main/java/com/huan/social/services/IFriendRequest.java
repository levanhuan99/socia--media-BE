package com.huan.social.services;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;

import java.util.List;
import java.util.Optional;

public interface IFriendRequest {
    void save(FriendRequest request);


    FriendRequest findFrienRequestByAccount(Integer accountSenderId,Integer accountReciverId);
}
