package com.huan.social.repositories;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Query("select f from FriendRequest f where f.acccountReciverId = ?1 and f.acccountSenderId = ?2")
    FriendRequest findFrienRequestByAccount(Integer acccountReciverId, Integer acccountSenderId);

//    @Query("delete from FriendRequest where acccountReciverId = ?1 and acccountSenderId = ?2")
//    void deleteRequest(Integer acccountReciverId, Integer acccountSenderId);


}
