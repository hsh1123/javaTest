package com.example.googleproject.Comment.repository;


import com.example.googleproject.Comment.domain.cmtDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface cmtRepository {

    public List<cmtDto> getlist(cmtDto dto);

    public int addComment(cmtDto dto);

    public int addStep(cmtDto dto);

    public int commentAnswer(cmtDto dto);

}
