package com.shop.demo.service;

import com.shop.demo.pojo.Cusaddress;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface CusaddressService {
    public int add(Cusaddress cusaddress);
    public List<Cusaddress> selectByOpenid(String openid);
    public int delete(String id);
    public int update(String id,String address,int isdefault,String phone,String title);
}
