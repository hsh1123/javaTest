package com.example.googleproject.Chat.service;


import com.example.googleproject.Chat.domain.chatDto;
import com.example.googleproject.Chat.repository.chatRepository;
import com.example.googleproject.Member.domain.memberDto;
import com.example.googleproject.Member.repository.memberRepository;
import org.springframework.stereotype.Service;

@Service
public class chatService {

    private  memberRepository memberRepository;
    private chatRepository chatRepository;

    public chatDto chattingHandler(chatDto chatting) {
//        memberDto user = memberRepository.findById(chatting.getUser().getId())
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
//
//        chatting.setUser(user);
//
//        return chattingRepository.save(chatting);

        return null;
    }
}
