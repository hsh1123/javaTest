package com.example.googleproject.Chat.controller;


import com.example.googleproject.Chat.domain.chatDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class chatController {

    @MessageMapping("/receive")

    @SendTo("/send")

    public chatDto SocketHandler(chatDto dto) {

        // vo에서 getter로 userName을 가져옵니다.
        String userName = dto.getUser();
        // vo에서 setter로 content를 가져옵니다.
        String content = dto.getMessage();

        // 생성자로 반환값을 생성합니다.
        chatDto result = new chatDto(userName,content);
        // 반환
        return result;
    }
}
