package com.example.googleproject.utill;

import com.example.googleproject.Board.domain.boardDto;
import com.example.googleproject.Board.domain.productDto;
import com.example.googleproject.Member.domain.memberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

@Slf4j
public class Util {


    public String getHttpConnection(String uri) throws IOException {
        URL url = new URL(uri);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        System.out.println("response : "+responseCode);
        String line;
        StringBuffer buffer = new StringBuffer();
        try (InputStream stream = conn.getInputStream()) {
            try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
                while ((line = rd.readLine()) != null) {
                    buffer.append(line);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public memberDto memberDataTransform(memberDto dto){

        if(!dto.getId().equals("") || dto.getId() != null){

            dto.setAuth("n");

            if(dto.getFile()==null){
                dto.setFile("-");
            }
        }

        return dto;
    }

    public boardDto boardDataTransform(boardDto dto){

        if(!dto.getId().equals("") || dto.getId() != null){

            if(dto.getCategori()==null){
                dto.setCategori("-");
            }
        }

        return dto;
    }

    public String loginTesting(memberDto dto){

        String result="false";

        log.info("Util loginTesting dto.getId ::: {}",dto.getId());

        if(dto.getId().equals("") || dto.getId() == null){
            result = "false";
        }else{
            result = "true";
        }

        return result;
    }

    public productDto productDataTransform(productDto dto){

        if(!dto.getId().equals("") || dto.getId() != null){

            if(dto.getState()==null){
                dto.setState("판매중");
            }
        }else {

            log.debug("Util productDataTransform id is null");

        }


        return dto;
    }

}


