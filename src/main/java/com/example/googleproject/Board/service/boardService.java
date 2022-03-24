package com.example.googleproject.Board.service;

import com.example.googleproject.Board.domain.boardDto;
import com.example.googleproject.Board.domain.fileDto;
import com.example.googleproject.Board.domain.productDto;
import com.example.googleproject.Board.repository.boardRepository;
import com.example.googleproject.utill.File.FileUtil;
import com.example.googleproject.utill.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<String> mainAddList(MultipartHttpServletRequest req){

        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String status = req.getParameter("status");
        String exchange = req.getParameter("exchange");
        String money = req.getParameter("money");
        String categori = req.getParameter("categori");
        String place = req.getParameter("place");

        List<String> mainList = new ArrayList<>();
        productDto dto = new productDto(id,title,content,status,exchange,money,categori,place);
        productDto result;

        if(!dto.getId().equals("") || dto.getId() != null) {

            result = util.productDataTransform(dto);

            boardRepository.mainAddList(result);

            log.info("boardService mainAddList() Pno ::: {}",dto.getPno());

            try {

                List<Map<String,Object>> list = fileUtil.parseInsertFileInfo(dto.getPno(),req);

//                for(int i =0; i<list.size(); i++){
//                    Object key = list.get(i).get("pno");
//                    int a = (Integer)key;
//
//                    log.info("boardService mainAddList list key ::: {}",a);
//
//                }
                int seq=0;

                if(list.size()!=0){

                    Object key = list.get(0).get("pno");
                    seq= (Integer)key;

                }

                for(int i=0; i< list.size(); i++){

                    boardRepository.mainAddFile(list.get(i));
                }

                log.info("boardService mainAddList() pno ::: {}",dto.getPno());

                mainList = boardRepository.getMainFile(seq);

                for(int i = 0; i<mainList.size(); i++){
                    log.info("boardService mainAddList() mainList ::: {}",mainList.get(i));
                }


            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }

        }else{
            log.error("boardService mainAddList dto.getId() is null or empty");
        }

        return mainList;
    }

    public List<productDto> getProductList(){

        List<productDto> list = boardRepository.getProductList();

        return list;
    }

    public List<productDto> getProductDetail(productDto dto){

        List<productDto> list = boardRepository.getProductDetail(dto);

        return list;
    }

    public List<String> getMainFile(int pno){

        return boardRepository.getMainFile(pno);

    }

}
