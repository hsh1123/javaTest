package com.example.googleproject.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<Void> test(@RequestPart("files")List<MultipartFile> files, @RequestParam("jsonList")String jsonList) throws JsonProcessingException {

        String result="false";

        log.info("files count ::: {}",files.size());
        log.info("json list ::: {}",jsonList);

        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new SimpleModule());

        List<testDto> testDtos = objectMapper.readValue(jsonList, new TypeReference<List<testDto>>() {});




        return new ResponseEntity<>(HttpStatus.OK);
    }
}
