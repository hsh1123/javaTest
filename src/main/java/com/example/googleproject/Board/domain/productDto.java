package com.example.googleproject.Board.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class productDto {

    private int pno;
    private String id;
    private String title;
    private String content;
    private String categori;
    private String place;
    private String state; // 게시물의 상태
    private String exchange;
    private String status; // 상품의 상태
    private String money;
    private Date regiDate;
    private Date endDate;

    public productDto(String id, String title, String content, String categori,String place, String status, String exchange,String money){
        this.id = id;
        this.title = title;
        this.content = content;
        this.categori = categori;
        this.place = place;
        this.status = status;
        this.exchange = exchange;
        this.money = money;
    }


}
