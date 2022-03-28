package com.example.googleproject.Member.repository;

import com.example.googleproject.Member.domain.memberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface memberRepository {

    //회원가입
    boolean regi(memberDto dto);

    //기존 회원 구분
    int getId(String id);

    //login 정보 확인
    int getLogin(memberDto dto);

    //seq 가져오기(id,pwd)
    int getToken(memberDto dto);

    int getSeq(memberDto dto);

    //가입과 동시에 seq 가져오기
    int memberRegi(memberDto dto);

    //seq로 member data 다 불러 오기
    List<memberDto> getMemberData(memberDto dto);

    List<memberDto> updateProfile(memberDto dto);
}
