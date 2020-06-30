package com.shop.demo.service.impl;

import com.shop.demo.dao.ProductimgsMapper;
import com.shop.demo.pojo.Product;
import com.shop.demo.pojo.Productimgs;
import com.shop.demo.service.ProductimgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfuctimgsImpl implements ProductimgsService {
    @Autowired
    ProductimgsMapper productimgsMapper;

    @Override
    public int updateByPrimaryKeySelective(Productimgs record) {
        return productimgsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Productimgs> selectByproductid(String productid) {
        return productimgsMapper.selectByproductid(productid);
    }

    @Override
    public Productimgs selectByPrimaryKey(String imgid) {
        return productimgsMapper.selectByPrimaryKey(imgid);
    }

    @Override
    public int insert(Productimgs record) {
        return productimgsMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(String imgid) {
        return productimgsMapper.deleteByPrimaryKey(imgid);
    }
}
