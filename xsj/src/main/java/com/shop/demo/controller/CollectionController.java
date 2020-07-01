package com.shop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.demo.dto.CollectionDetail;
import com.shop.demo.pojo.Collection;
import com.shop.demo.service.CollectionService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(allowCredentials = "true",allowedHeaders = "*" , origins = "*")
@RestController
public class CollectionController {
    @Autowired
    public CollectionService collectionService;
    /**
     * 添加收藏
     * productid：商品id
     * openid:用户标识
     * @param
     * @return
     */
    @PostMapping("/collection/add")
    public Map<String,Object> addCollection(@RequestBody JSONObject json){
        Collection collection = new Collection();
        Map<String,Object> map = new HashMap<>();
        collection.setId(UUID.randomUUID().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatStr =formatter.format(new Date());
        collection.setCollecttime(formatStr);

        String pId = json.getString("productid");
        String openid = json.getString("openid");
        collection.setOpenid(openid);
        collection.setProductid(pId);
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

    /**
     * 移除收藏
     * productid：商品id
     * openid:用户标识
     * @param1:productid
     * @param2:openid
     * @return
     */
    @DeleteMapping("/collection/delete")
    public Map<String,Object> deleteCollection(@RequestBody JSONObject json){
        Map<String,Object> map = new HashMap<>();
        String pId = json.getString("productid");
        String openid = json.getString("openid");
        try {
            int res = collectionService.deleteCollection(pId,openid);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","移除成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","移除失败");
        }
        return map;
    }

    /**
     * 查询收藏
     * @param:openid
     * @return
     */
    @GetMapping("/collection/select")
    public Map<String,Object> selectCollection(String openid){
        Map<String,Object> map = new HashMap<>();
        try {
            List<CollectionDetail> res = collectionService.selectCollection(openid);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","查询成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","查询失败");
        }
        return map;
    }
}
