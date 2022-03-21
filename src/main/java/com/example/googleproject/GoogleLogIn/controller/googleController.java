package com.example.googleproject.GoogleLogIn.controller;

import com.example.googleproject.utill.Name.Client;
import com.example.googleproject.utill.Util;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
public class googleController extends HttpServlet {

    String decodedCode = "";

    @PostMapping(value = "/redirect")
    public boolean googleAuth(@RequestBody Map authCode) throws Exception {

        boolean result = false;
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
        String user = util.getHttpConnection("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken);

        System.out.println("user : "+ user);

        return result;
    }
}
