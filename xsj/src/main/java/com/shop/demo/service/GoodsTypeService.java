package com.shop.demo.service;

import com.shop.demo.pojo.Goodstype;

import java.util.List;

public interface GoodsTypeService {
    int deleteByPrimaryKey(String id);

    int insert(Goodstype record);

    int insertSelective(Goodstype record);

    Goodstype selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goodstype record);

    int updateByPrimaryKey(Goodstype record);

    List<Goodstype> showAll();

    Goodstype selectByname(String name);
}
