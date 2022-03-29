package com.example.googleproject.Member.controller;

import com.example.googleproject.Member.domain.memberDto;
import com.example.googleproject.Member.service.memberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class memberController {

    @Autowired
    memberService memberService;

    @PostMapping("/regi")
    public int regi(@RequestBody memberDto dto){

        log.info("memberController regi {}",dto);

        int result = memberService.regi(dto);

        return result;
    }

    @PostMapping("/login")
    public String login(@RequestBody memberDto dto){

        log.info("memberController login {}",dto);

        int seq = memberService.getToken(dto);

        return seq+"";
    }

    //sequence, get member Data
    @PostMapping("/member/data")
    public List<memberDto> getMemberData(@RequestBody memberDto dto){

        log.info("memberController getMemberData mno ::: {}",dto.getMno());

        List<memberDto> result = memberService.getMemberData(dto);

        log.info("memberController getMemberData dto size ::: {}",result.size());

        return result;
    }

    @PostMapping("/member/update")
    public String updateProfile(@RequestBody memberDto dto){

        String result = "false";
        String check = memberService.updateProfile(dto);
        if(check.equals("true")){
            result = "true";
        }
        return result;
    }

    @PostMapping("member/sequence")
    public int getSeq(@RequestBody memberDto dto){

        log.info("memberController getSeq ::: {}",dto);

        int seq = 0;

        seq = memberService.getSeq(dto);

        return seq;
    }

    @PostMapping("member/idCheck")
    public boolean idCheck(@RequestBody memberDto dto){

        log.info("memberController idCheck dto ::: {}",dto);

        boolean check = false;

        boolean cnt = memberService.idCheck(dto);

        if(cnt){

            check = true;
        }

        return check;
    }
}
