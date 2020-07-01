package com.shop.demo.service.impl;

import com.shop.demo.dao.GoodstypeMapper;
import com.shop.demo.pojo.Goodstype;
import com.shop.demo.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsTypeImpl implements GoodsTypeService {
    @Autowired
    GoodstypeMapper goodstypeMapper;
    @Override
    public int deleteByPrimaryKey(String id) {
        return goodstypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goodstype record) {
        return goodstypeMapper.insert(record);
    }

    @Override
    public int insertSelective(Goodstype record) {
        return goodstypeMapper.insertSelective(record);
    }

    @Override
    public Goodstype selectByPrimaryKey(String id) {
        return goodstypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goodstype record) {
        return goodstypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goodstype record) {
        return goodstypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Goodstype> showAll() {
        return goodstypeMapper.showAll();
    }

    @Override
    public Goodstype selectByname(String name) {
        return goodstypeMapper.selectByname(name);
    }
}
