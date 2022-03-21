package com.example.googleproject.Chat.domain;

import com.example.googleproject.Member.domain.memberDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class chatDto {

    private String user;
    private String message;

}
