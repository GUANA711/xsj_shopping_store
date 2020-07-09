package com.shop.demo.service;

import com.shop.demo.pojo.Product;

import java.util.List;

public interface ProductService {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> show();

    List<Product> selectBySelective(Product record);

    int addStock(String id);

    int decreaseStoke(String id);
}
