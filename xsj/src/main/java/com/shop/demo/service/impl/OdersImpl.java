package com.shop.demo.service.impl;

import com.shop.demo.controller.OrderManageController;
import com.shop.demo.dao.OrdersMapper;
import com.shop.demo.dto.OdersDto;
import com.shop.demo.pojo.Orders;
import com.shop.demo.service.OdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdersImpl implements OdersService {
    @Autowired
    OrdersMapper ordersMapper;
    @Override
    public int deleteByPrimaryKey(String id) {
        return ordersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Orders record) {
        return ordersMapper.insert(record);
    }

    @Override
    public int insertSelective(Orders record) {
        return ordersMapper.insertSelective(record);
    }

    @Override
    public Orders selectByPrimaryKey(String id) {
        return ordersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Orders record) {
        return ordersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orders record) {
        return ordersMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<OdersDto> selectBySelective(Orders orders) {
        return ordersMapper.selectBySelective(orders);
    }
}
