package com.example.googleproject.Board.controller;

import com.example.googleproject.Board.domain.boardDto;
import com.example.googleproject.Board.domain.fileDto;
import com.example.googleproject.Board.service.boardService;
import com.example.googleproject.utill.File.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class BoardController {

    @Autowired
    boardService boardService;

    FileUtil fileUtil = new FileUtil();

    @PostMapping("/board/list")
    public List<boardDto> findAll(){

        List<boardDto> list = boardService.allBoard();

        return list;
    }

    @PostMapping("/board/detail")
    public List<boardDto> detail(@RequestBody boardDto dto){

        log.info("BoardController findOne bno ::: {}",dto.getBno());

        List<boardDto> list = boardService.detail(dto);

        return list;

    }

    @PostMapping("/board/write")
    public String write(MultipartHttpServletRequest req){

        String result = "false";
        String status = "";

        status = boardService.addList(req);

        log.info("BoardController write status ::: {}",status);

        if(status.equals("true")){
            result="true";
        }

       return result;
    }

    @PostMapping("/board/detail/file")
    public List selectFile(@RequestBody boardDto dto){

        log.info("BoardController slectFile() board Number::: {}",dto.getBno());

        List<Map<String, Object>> fileList = boardService.selectFile(dto.getBno());

        for(int i=0; i<fileList.size(); i++){
            Map<String,Object> map = fileList.get(i);
            map.toString();
            log.info("BoardController selectFile() file map::: {}",map);
        }

        return fileList;
    }

    @PostMapping("/fileDown")
    public String fileDown(@RequestBody fileDto dto, HttpServletResponse response, HttpServletRequest request){

        String result = "false";

        log.info("BoardController fileDown dto ::: {}",dto.getFno());

        Map<String, Object> resultMap = boardService.getFile(dto);

        result = fileUtil.fileDownLoad(resultMap,response,request);

        byte fileByte[] = new byte[0];

        return result;
    }

}
