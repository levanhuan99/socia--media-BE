package com.huan.social.repositories;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {
    Optional<FriendRequest> findFriendRequestByAcccountReciverAndAcccountSender(Account accountReciver , Account accountSender);

    Optional<FriendRequest> findByAcccountReciverAndAcccountSender(Account accountReciver , Account accountSender);

    List<FriendRequest> findAllByAcccountSender(Account account);

    List<FriendRequest> findAllByAcccountSenderAndAcccountReciver(Account accountReciver , Account accountSender);
}
