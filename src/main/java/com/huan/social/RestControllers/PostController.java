package com.huan.social.RestControllers;

import com.huan.social.models.Account;
import com.huan.social.models.FriendRequest;
import com.huan.social.models.Post;
import com.huan.social.services.impl.AccountService;
import com.huan.social.services.impl.FriendRequestService;
import com.huan.social.services.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private FriendRequestService friendRequestService;

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

    @GetMapping("")
    public ResponseEntity<List<Post>> getPosts(Long id){
        Optional<Account> account = accountService.findById(id);
        Integer Id = Math.toIntExact(id);
        if (account.isPresent()){
            List<FriendRequest> friendRequestList = this.friendRequestService.findAllFriend("Yes", Id, Id);
            if (friendRequestList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Long friendId = null;
            for (int i = 0; i < friendRequestList.size(); i++) {
                if (friendRequestList.get(i).getAcccountReciverId()!=Id){
                    friendId= Long.valueOf(friendRequestList.get(i).getAcccountReciverId());
                }
                else {
                    friendId= Long.valueOf(friendRequestList.get(i).getAcccountSenderId());
                }
            }
        }

        return null;
    }

}
