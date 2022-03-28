package com.example.googleproject.Comment.domain;

import lombok.*;

import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class cmtDto {

    private int pno;
    private int cno;
    private String id;
    private int ref;
    private int step;
    private int depth;
    private String comment;
    private Date wdate;
    private int del;

}
