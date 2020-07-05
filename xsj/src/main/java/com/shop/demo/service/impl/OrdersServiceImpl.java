package com.shop.demo.service.impl;

import com.shop.demo.dao.OrdersMapper;
import com.shop.demo.pojo.Orders;
import com.shop.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    public OrdersMapper ordersMapper;

    @Override
    public int add(Orders orders) {
        return ordersMapper.insert(orders);
    }

    @Override
    public List<Orders> select(String openid, int ispay, int receive, String productid) {
        return ordersMapper.select(openid,ispay,receive,productid);
    }

    @Override
    public int updateIsPay(String openid, String productid) {
        return ordersMapper.updateIspay(openid,productid);
    }

    @Override
    public int updateReceice(String openid, String productid) {
        return ordersMapper.updateReceice(openid,productid);
    }

    @Override
    public int updateReceiceByMan(String openid, String productid) {
        return ordersMapper.updateReceiceByMan(openid,productid);
    }

    @Override
    public int cancel(String openid, String productid) {
        return ordersMapper.cancel(openid,productid);
    }
}
