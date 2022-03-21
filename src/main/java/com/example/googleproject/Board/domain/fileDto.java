package com.example.googleproject.Board.domain;

import lombok.Data;

@Data
public class fileDto {

    private int fno;
    private int bno;
    private String org_file_name;
    private String str_file_name;
    private String file_path;
    private long file_size;

}
