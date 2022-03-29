package com.example.googleproject.Item.repository;


import com.example.googleproject.Item.domain.categoriDto;
import com.example.googleproject.Item.domain.placeDto;
import com.example.googleproject.Item.domain.priceDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface itemRepository {

    List<categoriDto> getCategori();
    List<placeDto> getPlace();
    List<priceDto> getPrice();

}
