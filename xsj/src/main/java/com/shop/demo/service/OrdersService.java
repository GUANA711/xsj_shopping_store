package com.shop.demo.service;

import com.shop.demo.dto.OrdersDetail;
import com.shop.demo.pojo.Orders;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface OrdersService {
    public int add(Orders orders);
    public List<OrdersDetail> select(String openid, int ispay, int receive, String productid);
    public int updateIsPay(String id);
    public int updateReceice(String id);
    public int updateReceiceByMan(String id);
    public int cancel(String id);
}
