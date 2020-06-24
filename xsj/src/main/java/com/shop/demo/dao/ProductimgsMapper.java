package com.shop.demo.dao;

import com.shop.demo.pojo.Productimgs;

public interface ProductimgsMapper {
    int deleteByPrimaryKey(String imgid);

    int insert(Productimgs record);

    int insertSelective(Productimgs record);

    Productimgs selectByPrimaryKey(String imgid);

    int updateByPrimaryKeySelective(Productimgs record);

    int updateByPrimaryKey(Productimgs record);
}