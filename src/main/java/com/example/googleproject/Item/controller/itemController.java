package com.example.googleproject.Item.controller;

import com.example.googleproject.Item.domain.categoriDto;
import com.example.googleproject.Item.domain.placeDto;
import com.example.googleproject.Item.domain.priceDto;
import com.example.googleproject.Item.service.itemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class itemController {

    @Autowired
    itemService itemService;

    @PostMapping("/item/all")
    public Map<String,Object> allItem(){

        Map<String,Object> map = new HashMap<>();

        List<categoriDto> ctgList = itemService.getCategori();
        List<priceDto> prcList = itemService.getPrice();
        List<placeDto> plcList = itemService.getPlace();

        map.put("categori",ctgList);
        map.put("price",prcList);
        map.put("place",plcList);

        return map;
    }




}
