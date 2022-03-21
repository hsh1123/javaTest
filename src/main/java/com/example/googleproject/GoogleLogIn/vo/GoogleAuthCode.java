package com.example.googleproject.GoogleLogIn.vo;


import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class GoogleAuthCode {

    private String authCode;

    @Builder
    public GoogleAuthCode(String authCode){
        this.authCode = authCode;
    }
}
