package com.huan.social.repositories;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Query("select f from FriendRequest f where f.acccountReciverId = ?1 and f.acccountSenderId = ?2")
    FriendRequest findFrienRequestByAccount(Integer acccountReciverId, Integer acccountSenderId);

}
