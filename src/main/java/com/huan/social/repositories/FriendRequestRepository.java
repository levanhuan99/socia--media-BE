package com.huan.social.repositories;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Query("select f from FriendRequest f where f.acccountReciverId = ?1 and f.acccountSenderId = ?2")
    FriendRequest findFrienRequestByAccountSenderandAccountReceiver(Integer acccountReciverId, Integer acccountSenderId);

//    @Query("delete from FriendRequest where acccountReciverId = ?1 and acccountSenderId = ?2")
//    void deleteRequest(Integer acccountReciverId, Integer acccountSenderId);

    @Query("select f from FriendRequest f where f.friendStatus= ?1 and (f.acccountReciverId = ?2 or f.acccountSenderId=?3)")
    List<FriendRequest> findAllFriend(String status, Integer accountReceiverId, Integer accountSenderId);


}
