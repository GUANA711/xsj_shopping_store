package com.shop.demo.service.impl;

import com.shop.demo.dao.OrdersMapper;
import com.shop.demo.dto.OrdersDetail;
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
    public List<OrdersDetail> select(String openid, int ispay, int receive, String productid) {
        return ordersMapper.select(openid,ispay,receive,productid);
    }

    @Override
    public int updateIsPay(String id) {
        return ordersMapper.updateIspay(id);
    }

    @Override
    public int updateReceice(String id) {
        return ordersMapper.updateReceice(id);
    }

    @Override
    public int updateReceiceByMan(String id) {
        return ordersMapper.updateReceiceByMan(id);
    }

    @Override
    public int cancel(String id) {
        return ordersMapper.cancel(id);
    }
}
