package com.huan.social.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String friendStatus; //Yes,Pending,No


    @JoinColumn(name = "acccountReciverId")
    private Integer acccountReciverId;


    @JoinColumn(name = "acccountSenderId")
    private Integer acccountSenderId;

    public FriendRequest(){

    }

}
