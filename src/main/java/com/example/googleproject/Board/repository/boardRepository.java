package com.example.googleproject.Board.repository;

import com.example.googleproject.Board.domain.boardDto;
import com.example.googleproject.Board.domain.fileDto;
import com.example.googleproject.Board.domain.productDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface boardRepository {

    List<boardDto> allBoard();

    List<boardDto> detail(boardDto dto);

    boolean addList(boardDto dto);

    boolean addFile(Map<String,Object> map);

    List<Map<String, Object>> selectFile(int bno);

    Map<String, Object> getFile(fileDto dto);

    boolean mainAddList(productDto dto);

    boolean mainAddFile(Map<String,Object> map);

    List<String> getMainFile(int pno);

}
