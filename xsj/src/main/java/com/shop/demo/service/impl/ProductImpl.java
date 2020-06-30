package com.shop.demo.service.impl;

import com.shop.demo.dao.ProductMapper;
import com.shop.demo.pojo.Product;
import com.shop.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public int deleteByPrimaryKey(String id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Product> show() {
        return productMapper.show();
    }

    @Override
    public List<Product> selectBySelective(Product record) {
        return productMapper.selectBySelective(record);
    }

    @Override
    public int insert(Product record) {
        return productMapper.insert(record);
    }

    @Override
    public int insertSelective(Product record) {
        return productMapper.insertSelective(record);
    }

    @Override
    public Product selectByPrimaryKey(String id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Product record) {
        return productMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        return productMapper.updateByPrimaryKey(record);
    }
}
