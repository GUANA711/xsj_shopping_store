package com.shop.demo.dao;

import com.shop.demo.pojo.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(String openid);

    int insert(Customer record);

    int insertSelective(Customer record);
}