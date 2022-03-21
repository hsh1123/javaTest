package com.example.googleproject.Board.service;

import com.example.googleproject.Board.domain.boardDto;
import com.example.googleproject.Board.domain.fileDto;
import com.example.googleproject.Board.repository.boardRepository;
import com.example.googleproject.utill.File.FileUtil;
import com.example.googleproject.utill.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.*;

@Slf4j
@Service
public class boardService {

    @Autowired
    boardRepository boardRepository;

    @Autowired
    FileUtil fileUtil;

    Util util = new Util();

    public List<boardDto> allBoard(){

//      log.info("boardService all Board ::: {}", boardRepository.allBoard());

      return boardRepository.allBoard();
    }

    public List<boardDto> detail(boardDto dto){

        log.info("boardService one Board bno ::: {}",dto.getBno());

        List<boardDto> result = boardRepository.detail(dto);

        log.info("boardService one Board ::: {}", result);

        return boardRepository.detail(dto);
    }

    public String addList(MultipartHttpServletRequest req){


        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        String      result      =   "false";
        boolean     addStatus   =   false;

        boardDto dto = new boardDto(id,title,content);
        boardDto resultDto;


        if(!dto.getId().equals("") || dto.getId() != null) {

            resultDto = util.boardDataTransform(dto);
            addStatus = boardRepository.addList(resultDto);

            log.info("boardService addList() test ::: {}",dto.getBno());

            try {
               List<Map<String,Object>> list = fileUtil.parseInsertFileInfo(dto.getBno(),req);

               for(int i=0; i< list.size(); i++){

                   boardRepository.addFile(list.get(i));

               }
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }

        }else{

            log.error("dto.getId() is null or empty");

        }

        if(addStatus){
            result = "true";
        }
        log.debug("");
        return result;
    }

    public List<Map<String, Object>> selectFile(int bno) {

        List<Map<String,Object>> list = new ArrayList<>();

        if(bno != 0){

            list = boardRepository.selectFile(bno);

        }else{

            log.debug("boardService selectFile() bno is null");

        }

        return list;

    }

    public Map<String, Object> getFile(fileDto dto){

        Map<String,Object> test = boardRepository.getFile(dto);

//        Iterator<String> keys = test.keySet().iterator();
//
//        while(keys.hasNext()){
//            String key = keys.next();
//            System.out.println( String.format("키 : %s, 값 : %s", key, test.get(key)) );
//
//        }


        return test;
    }

}
