package com.huan.social.RestControllers;

import com.huan.social.models.Account;
import com.huan.social.models.Post;
import com.huan.social.services.impl.AccountService;
import com.huan.social.services.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<?> createPost(@RequestBody Post post){
        if (post.getAccount().getId()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Date date = new Date();
        date.setYear(date.getYear());
        date.setMonth(date.getMonth());
        date.setDate(date.getDay());
        date.setHours(date.getHours());
        post.setPostTime(date);
        Optional<Account> account=this.accountService.findById(post.getAccount().getId());
        post.setAccount(account.get());
        this.postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
