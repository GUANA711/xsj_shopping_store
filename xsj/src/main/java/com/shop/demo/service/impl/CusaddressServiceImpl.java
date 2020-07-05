package com.shop.demo.service.impl;

import com.shop.demo.dao.CusaddressMapper;
import com.shop.demo.pojo.Cusaddress;
import com.shop.demo.service.CusaddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CusaddressServiceImpl implements CusaddressService {
    @Autowired
    public CusaddressMapper cusaddressMapper;

    @Override
    public int add(Cusaddress cusaddress) {
        return cusaddressMapper.insert(cusaddress);
    }

    @Override
    public List<Cusaddress> selectByOpenid(String openid) {
        return cusaddressMapper.selectByOpenid(openid);
    }

    @Override
    public int delete(String id) {
        return cusaddressMapper.delete(id);
    }

    @Override
    public int update(String id, String address, int isdefault, String phone, String title) {
        return cusaddressMapper.update(id,address,isdefault,phone,title);
    }
}
