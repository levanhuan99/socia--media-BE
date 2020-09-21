package com.huan.social.services.impl;

import com.huan.social.models.FriendRequest;
import com.huan.social.repositories.FriendRequestRepository;
import com.huan.social.services.IFriendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService implements IFriendRequest {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Override
    public void save(FriendRequest request) {
        friendRequestRepository.save(request);
    }

    @Override
    public void delete(FriendRequest friendRequest) {
        this.friendRequestRepository.delete(friendRequest);
    }

    @Override
    public List<FriendRequest> findAllFriend(String status, Integer accountReceiverId, Integer accountSenderId) {
        return this.friendRequestRepository.findAllFriend(status,accountReceiverId,accountSenderId);
    }

    @Override
    public List<FriendRequest> findRequests(String status, Integer receiverId) {
        return this.friendRequestRepository.findRequests(status,receiverId);
    }

    @Override
    public FriendRequest findFrienRequestByAccountSenderAndAccountReceiver1(Integer accountSenderId, Integer accountReceiverId) {
        return this.friendRequestRepository.findFrienRequestByAccountSenderandAccountReceiver1(accountSenderId,accountReceiverId);
    }

    @Override
    public FriendRequest findFriendRequestByAccountSenderAndAccountReceiver2(Integer accountReceiverId, Integer accountSenderId) {
        return this.friendRequestRepository.findFrienRequestByAccountSenderandAccountReceiver2(accountReceiverId,accountSenderId);
    }

    @Override
    public FriendRequest findFriend(Integer id1, Integer id2, Integer id3, Integer id4) {
        return this.friendRequestRepository.findFriendRequestByAcccountReciverIdAndAcccountSenderIdOrAcccountSenderIdAndAcccountReciverId(id1,id2,id3,id4);
    }

}
