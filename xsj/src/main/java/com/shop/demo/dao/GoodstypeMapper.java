package com.shop.demo.dao;

import com.shop.demo.pojo.Goodstype;

public interface GoodstypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Goodstype record);

    int insertSelective(Goodstype record);

    Goodstype selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goodstype record);

    int updateByPrimaryKey(Goodstype record);
}