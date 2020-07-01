package com.shop.demo.controllers;

import com.shop.demo.pojo.Collection;
import com.shop.demo.services.CollectionService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(allowCredentials = "true",allowedHeaders = "*" , origins = "*")
@RestController
public class CollectionController {
    @Autowired
    public CollectionService collectionService;
    @PostMapping("/addCollection")
    public Map<String,Object> addCollection(Collection collection){
        Map<String,Object> map = new HashMap<>();
        collection.setId(UUID.randomUUID().toString());
        collection.setCollecttime(new Date());
        try {
            int res = collectionService.addCollection(collection);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","添加成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","添加失败");
        }
        return map;
    }

}
