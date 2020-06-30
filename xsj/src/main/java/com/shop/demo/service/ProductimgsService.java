package com.shop.demo.service;


import com.shop.demo.pojo.Productimgs;

import java.util.List;

public interface ProductimgsService {
    int insert(Productimgs record);
    int deleteByPrimaryKey(String imgid);
    Productimgs selectByPrimaryKey(String imgid);
    int updateByPrimaryKeySelective(Productimgs record);
    List<Productimgs> selectByproductid(String productid);
}
