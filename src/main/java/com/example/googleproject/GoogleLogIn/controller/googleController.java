package com.example.googleproject.GoogleLogIn.controller;

import com.example.googleproject.GoogleLogIn.service.googleLoginService;
import com.example.googleproject.Member.domain.memberDto;
import com.example.googleproject.Member.service.memberService;
import com.example.googleproject.utill.Name.Client;
import com.example.googleproject.utill.Util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class googleController extends HttpServlet {

    String decodedCode = "";

    @Autowired
    googleLoginService googleLoginService;

    @Autowired
    memberService memberService;

    @PostMapping(value = "/redirect")
    public JSONObject googleAuth(@RequestBody Map authCode) throws Exception {

        Util util = new Util();

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new FileReader(new Client().getCLIENT_FILE()));
        String token = authCode.get("authCode").toString();

        try {
            decodedCode = java.net.URLDecoder.decode(token, StandardCharsets.UTF_8.name());

        } catch (UnsupportedEncodingException e) {}

        GoogleTokenResponse tokenResponse =
                new GoogleAuthorizationCodeTokenRequest(
                        new NetHttpTransport(),
                        JacksonFactory.getDefaultInstance(),
                        "https://oauth2.googleapis.com/token",
                        clientSecrets.getDetails().getClientId(),
                        clientSecrets.getDetails().getClientSecret(),
                        decodedCode,
                        new Client().getREDIRECT_URL()
                ).execute();

        String accessToken = tokenResponse.getAccessToken();
        String user = util.getHttpConnection("https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken);

//        System.out.println("user : "+ user);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(user);
        Object objId = jsonObject.get("id");
        String userId = (String)objId;

        log.info("googleController json data ::: {}",userId);

        memberDto dto = new memberDto();
        dto.setId(userId);

        JSONObject result = googleLoginService.getLogin(dto);

        return result;

    }

    @PostMapping("google/regi")
    public int googleRegi(@RequestBody memberDto dto){

        log.info("memberController googleRegi ::: {}",dto);
        String result = "";
        int cnt =0;
        if(!dto.getId().equals("") && dto.getId() != null){
            String pwd = UUID.randomUUID()+"";

            dto.setPassword(pwd.replace("-","").substring(0,10));
            dto.setAuth("n");
            dto.setFile("-");
            dto.setEmail("-");

            cnt = memberService.memberRegi(dto);

        }


        return cnt;
    }
}
