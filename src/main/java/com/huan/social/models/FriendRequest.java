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

    private boolean friendStatus;

    @ManyToOne
    @JoinColumn (name = "acccountRecive")
    private Account acccountReciver;

    @ManyToOne
    @JoinColumn (name = "acccountSender")
    private Account acccountSender;



}
