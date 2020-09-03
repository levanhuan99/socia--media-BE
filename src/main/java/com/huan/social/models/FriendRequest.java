package com.huan.social.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FriendRequest {

    @Id
    private Long id;

    private String friendStatus; //Yes,Pending,No

    @ManyToOne
    @JoinColumn(name = "acccountReciver")
    private Account acccountReciver;

    @ManyToOne
    @JoinColumn(name = "acccountSender")
    private Account acccountSender;

}
