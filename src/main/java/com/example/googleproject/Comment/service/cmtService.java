package com.example.googleproject.Comment.service;


import com.example.googleproject.Comment.domain.cmtDto;
import com.example.googleproject.Comment.repository.cmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cmtService {

    @Autowired
    cmtRepository cmtRepository;

    public List<cmtDto> getList(cmtDto dto){
        return cmtRepository.getlist(dto);
    }

    public String addComment(cmtDto dto){

        return cmtRepository.addComment(dto) > 0 ? "true" : "false";
    }

    public String addStep(cmtDto dto){

        return  cmtRepository.addStep(dto) > 0 ? "true" : "false";
    }

    public String commentAnswer(cmtDto dto){

        return  cmtRepository.commentAnswer(dto) > 0 ? "true" : "false";
    }



}
