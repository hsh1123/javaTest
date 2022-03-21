package com.example.googleproject.utill.File;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Configuration
public class FileUtil {

    private final String filePath = "/Users/seonghohong/IdeaProjects/googleProject/frontend/src/assets/";

    public List<Map<String, Object>> parseInsertFileInfo(int bno,MultipartHttpServletRequest req) throws Exception{

        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        List<MultipartFile> data = req.getFiles("files");
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null;
        File file = new File(filePath);

        if(file.exists() == false) {
            file.mkdirs();
        }

        int fileSize = req.getFiles("files").size();

        log.info("FileUtil parseInsertFileInfo() fileSize::: {}",fileSize);
        log.info("FileUtil parseInsertFileInfo() dataLength::: {}",data.size());

        for(int i = 0; i<fileSize; i++ ){

                originalFileName = data.get(i).getOriginalFilename();

                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = getRandomString() + originalFileExtension;

                file = new File(filePath + storedFileName);

                data.get(i).transferTo(file);

                listMap = new HashMap<String, Object>();
                listMap.put("bno", bno);                            //board num
                listMap.put("org_file_name", originalFileName);     //파일명(원본)
                listMap.put("stored_file_name", storedFileName);    //파일명(수정)
                listMap.put("file_size", data.get(i).getSize());  //파일 크기
                list.add(listMap);

        }
        return list;
    }

    public static String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String fileDownLoad(Map<String, Object> file, HttpServletResponse response, HttpServletRequest req){


        String result = "false";
        String storedFileName = (String) file.get("stored_file_name");
        String originalFileName = (String) file.get("org_file_name");

        byte fileByte[] = new byte[0];

        try {

            fileByte = FileUtils.readFileToByteArray(new File("/Users/seonghohong/IdeaProjects/googleProject/frontend/src/assets/" + storedFileName));

            response.setContentType("application/octet-stream");
            response.setContentLength(fileByte.length);
            response.setHeader("Content-Disposition",  "attachment; fileName=\""+ URLEncoder.encode(originalFileName, "UTF-8")+"\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            response.getOutputStream().write(fileByte);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            result = "true";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }



}
