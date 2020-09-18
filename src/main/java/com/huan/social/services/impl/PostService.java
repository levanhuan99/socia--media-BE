package com.huan.social.services.impl;

import com.huan.social.models.Post;
import com.huan.social.repositories.PostRepository;
import com.huan.social.services.IPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPost {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void save(Post post) {
        this.postRepository.save(post);
    }
}
