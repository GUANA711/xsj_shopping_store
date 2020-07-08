package com.shop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.demo.pojo.Cusaddress;
import com.shop.demo.service.CusaddressService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(allowCredentials = "true",allowedHeaders = "*" , origins = "*")
@RestController
public class CusaddressController {
    @Autowired
    public CusaddressService cusaddressService;

    @GetMapping("/address/select")
    public Map<String,Object> select(@RequestParam("openid") String openid){
        Map<String,Object> map = new HashMap<>();
        try {
            List<Cusaddress> res = cusaddressService.selectByOpenid(openid);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","查询成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","查询失败");
        }
        return map;
    }

    @PostMapping("/address/add")
    public Map<String,Object> add(@RequestBody JSONObject json){
        Map<String,Object> map = new HashMap<>();

        Cusaddress cusaddress = new Cusaddress();
        cusaddress.setId(UUID.randomUUID().toString());

        String openid = json.getString("openid");
        String address = json.getString("address");
        int isdefault = Integer.parseInt(json.getString("isdefault"));
        String phone = json.getString("phone");
        String title = json.getString("title");

        cusaddress.setAddress(address);
        cusaddress.setIsdefault(isdefault);
        cusaddress.setOpenid(openid);
        cusaddress.setPhone(phone);
        cusaddress.setTitle(title);

        try{
            int res = cusaddressService.add(cusaddress);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","添加成功");
        } catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","添加失败");
        }
        return map;
    }

    @PostMapping("/address/delete")
    public Map<String,Object> delete(@RequestParam("id") String id){
        Map<String,Object> map = new HashMap<>();
        try {
            int res = cusaddressService.delete(id);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","删除成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","删除失败");
        }
        return map;
    }

    @PostMapping("/address/update")
    public Map<String,Object> update(@RequestBody JSONObject json){
        Map<String,Object> map = new HashMap<>();
        String id = json.getString("id");
        String address = json.getString("address");
        String openid = json.getString("openid");
        int isdefault;
        if(json.getString("isdefault")!=null){
            isdefault = Integer.parseInt(json.getString("isdefault"));
        }else {
            isdefault = -1;
        }
        String phone = json.getString("phone");
        String title = json.getString("title");
        if (isdefault == 1){
            try{
                int updateDefault = 0;
                String update = null;
                int res = cusaddressService.update(update,openid,update,updateDefault,update,update);
            }catch (SqlSessionException e){
                map.put("status",500);
                map.put("msg","更新失败");
                return map;
            }
        }
        try{
            int res = cusaddressService.update(id,openid,address,isdefault,phone,title);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","更新成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","更新失败");
        }
        return map;
    }
}
