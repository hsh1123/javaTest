package com.example.googleproject.Board.domain;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class boardDto {

    private int bno;
    private String id;
    private String title;
    private String content;
    private Date wdate;
    private String categori;

    public boardDto(String id, String title,String categori ,String content){
        this.id = id;
        this.title = title;
        this.categori = categori;
        this.content = content;
    }
}

