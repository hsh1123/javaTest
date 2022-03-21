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

    public String regi(memberDto dto){

        int cnt = memberRepository.getId(dto.getId());
        log.info("result ::: {}",cnt);
        String rst;

        if(cnt == 0){

            memberDto value = util.memberDataTransform(dto);

            log.info("userDto ::: {}",dto);

            boolean insertData = memberRepository.regi(value);

            if(insertData){
                rst = "true";
            }else{
                rst = "false";
            }

        }else{
            log.info("userDto ::: {}",dto);
            rst = "false";
        }
        return rst;

    }

//    public String getLogin(memberDto dto){
//
//        String testedData = util.loginPasswordTesting(dto);
//        String result="false";
//
//        if(testedData.equals("true")){
//
//            int cnt = memberRepository.getLogin(dto);
//
//            log.info("memberService getLogin cnt ::: {}",cnt);
//
//            if(cnt == 1){
//                result = "true";
//            }else if(cnt > 1){
//                result = "false";
//            }else{
//                result = "false";
//            }
//
//        }
//
//        log.info("memberService getLogin result ::: {}",result);
//
//        return result;
//
//    }

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

    public String updateProfile(memberDto dto){


        return "";
    }

}
