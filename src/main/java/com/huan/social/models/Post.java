package com.huan.social.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //nếu chạy lại thì cần phải có anotation để set long text cho content
    private String content;

    @Column(columnDefinition = "LONGTEXT")
    private Date postTime;


    private String status;

    private int amountOfLike;

    private int amountOfComment;

    @ManyToOne
    private Account account;

    public Post() {

    }
}
