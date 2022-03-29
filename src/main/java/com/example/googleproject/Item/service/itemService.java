package com.example.googleproject.Item.service;

import com.example.googleproject.Item.domain.categoriDto;
import com.example.googleproject.Item.domain.placeDto;
import com.example.googleproject.Item.domain.priceDto;
import com.example.googleproject.Item.repository.itemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class itemService {

    @Autowired
    itemRepository itemRepository;

    public List<categoriDto> getCategori(){
        return itemRepository.getCategori();
    }

    public List<placeDto> getPlace(){
        return itemRepository.getPlace();
    }

    public List<priceDto> getPrice(){
        return itemRepository.getPrice();
    }
}
