package com.example.googleproject.Comment.controller;

import com.example.googleproject.Comment.domain.cmtDto;
import com.example.googleproject.Comment.service.cmtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api")
public class cmtController {

    @Autowired
    cmtService cmtService;

    @PostMapping("/comment/list")
    public List<cmtDto> commentList(@RequestBody cmtDto dto) {

        List<cmtDto> list = new ArrayList<>();

        list = cmtService.getList(dto);

        return list;
    }

    @PostMapping( "/comment/add")
    public String commentAdd(@RequestBody cmtDto dto) {

        log.info("cmtController commentAdd dto ::: {}",dto.toString());

        String result;

        result = cmtService.addComment(dto);

        return result;
    }

    @PostMapping( "/comment/answer")
    public String commentAnswer(@RequestBody cmtDto dto) {

        String addStep = cmtService.addStep(dto);
        String addAnswer = cmtService.commentAnswer(dto);
        String result="false";

        if(addAnswer.equals("true")){
            result="true";
        }



        return result;
    }

}
