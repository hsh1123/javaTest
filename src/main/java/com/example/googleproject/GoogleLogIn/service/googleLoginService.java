package com.example.googleproject.GoogleLogIn.service;

import com.example.googleproject.Member.domain.memberDto;
import com.example.googleproject.Member.repository.memberRepository;
import com.example.googleproject.utill.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class googleLoginService {

    @Autowired
    memberRepository memberRepository;

    Util util = new Util();

    public JSONObject getLogin(memberDto dto){

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        String testedData = util.loginTesting(dto);

        String result="false";
        int cnt=0;

        log.info("googleLoginService getLogin testedData ::: {}",testedData);

        if(testedData.equals("true")) {

            cnt = memberRepository.getLogin(dto);
            String idCode = dto.getId();
            String jsonResult = "";


            if (cnt == 0) {
                jsonResult = "{\"status\":\"false\",\"id\":\""+idCode+"\"}";
            } else {
                jsonResult = "{\"status\":\"true\",\"id\":\""+idCode+"\"}";
            }


            try {
                jsonObject = (JSONObject) parser.parse(jsonResult);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        log.info("memberService getLogin result ::: {}",jsonObject);

        return jsonObject;

    }

}
