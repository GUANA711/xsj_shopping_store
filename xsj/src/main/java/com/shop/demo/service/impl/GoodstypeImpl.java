package com.shop.demo.service.impl;

import com.shop.demo.dao.GoodstypeMapper;
import com.shop.demo.pojo.Goodstype;
import com.shop.demo.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alice
 */
@Service
public class GoodstypeImpl implements GoodsTypeService {

    @Autowired
    private GoodstypeMapper goodstypeMapper;
    @Override
    public Goodstype selectGoodsTypeById(String id) {
        return null;
    }
}
