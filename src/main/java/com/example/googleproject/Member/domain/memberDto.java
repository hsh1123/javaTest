package com.example.googleproject.Member.domain;


import lombok.Data;

import java.util.Date;

@Data
public class memberDto {

    private int mno;
    private String id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String categori;
    private String price;
    private String place;
    private String file;
    private String auth;
    private Date regiDate;

}
