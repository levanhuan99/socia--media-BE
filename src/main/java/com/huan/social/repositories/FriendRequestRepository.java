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
    FriendRequest findFrienRequestByAccountSenderandAccountReceiver1(Integer acccountReciverId, Integer acccountSenderId);

    @Query("select f from FriendRequest f where f.acccountSenderId = ?1 and f.acccountReciverId = ?2")
    FriendRequest findFrienRequestByAccountSenderandAccountReceiver2(Integer acccountReciverId, Integer acccountSenderId);

    @Query("select f from FriendRequest f where f.friendStatus= ?1 and (f.acccountReciverId = ?2 or f.acccountSenderId=?3)")
    List<FriendRequest> findAllFriend(String status, Integer accountReceiverId, Integer accountSenderId);

    @Query("select f from FriendRequest f where f.friendStatus= ?1 and f.acccountReciverId = ?2")
    List<FriendRequest> findRequests(String status, Integer receiverId);

//    @Query(value = "select friend_status from friend_request f where (f.acccount_reciver_id = ?1 and f.acccount_sender_id = ?2) or  (f.acccount_sender_id = ?1 and f.acccount_reciver_id = ?2)",nativeQuery = true)
    FriendRequest findFriendRequestByAcccountReciverIdAndAcccountSenderIdOrAcccountSenderIdAndAcccountReciverId(Integer id1, Integer id2,Integer if3, Integer id4);

}
