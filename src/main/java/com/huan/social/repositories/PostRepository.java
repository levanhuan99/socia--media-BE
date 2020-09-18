package com.huan.social.repositories;

import com.huan.social.models.FriendRequest;
import com.huan.social.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
