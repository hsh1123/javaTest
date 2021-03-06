package com.example.googleproject.Member.service;

import com.example.googleproject.Member.domain.memberDto;
import com.example.googleproject.Member.repository.memberRepository;
import com.example.googleproject.utill.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class memberService {

    @Autowired
    memberRepository memberRepository;

    Util util = new Util();

    public int regi(memberDto dto){

        int cnt = memberRepository.getId(dto.getId());
        log.info("result ::: {}",cnt);

        int insertData=0;

        if(cnt == 0){

            memberDto value = util.memberDataTransform(dto);

            log.info("userDto ::: {}",dto);

            insertData = memberRepository.memberRegi(value);

            if(insertData==1){
                return value.getMno();
            }else{
                return 0;
            }

        }else{
            log.info("userDto ::: {}",dto);
            return 0;

        }


    }

    public int getToken(memberDto dto){

        int seq;

        try{
            seq = memberRepository.getToken(dto);
        }catch (BindingException be){
            seq=0;
            log.info("memberService getToken catch seq ::: {}",seq);
        }

        log.info("memberService getToken seq ::: {}",seq);

        return seq;
    }

    public List<memberDto> getMemberData(memberDto dto){

        List<memberDto> result = new ArrayList<>();

        log.info("memberService getMemberData mno ::: {}",dto.getMno());

        if(dto.getMno() != 0){

            result = memberRepository.getMemberData(dto);

            log.info("memberService getMemberData mno ::: {}",dto.getMno());
        }

        return result;
    }

    public int getSeq(memberDto dto){

        int seq = 0;

        if(dto.getId().equals("")|| dto.getId() != null){

            seq = memberRepository.getSeq(dto);

        }

        return seq;

    }

    public int memberRegi(memberDto dto){

        log.info("memberService memberRegi dto ::: {}",dto);

        int result = memberRepository.memberRegi(dto);

//        log.info("memberService memberRegi mno ::: {}",dto.getMno());

        if(result==1){
            return dto.getMno();
        }else{
            return 0;
        }
    }

    public String updateProfile(memberDto dto){

        log.info("memberService updateProfile() dto.getId ::: {}",dto);

        if(!dto.getName().equals("") || dto.getName()!=null){

            memberDto result = util.memberDataTransform(dto);

            return memberRepository.updateProfile(result) > 0 ? "true" : "false";

        }
        else{

            log.info("memberService updateProfile dto is null");
            return "false";

        }

    }

    public boolean idCheck(memberDto dto){

        return memberRepository.getId(dto.getId()) > 0 ? true : false;

    }


}
