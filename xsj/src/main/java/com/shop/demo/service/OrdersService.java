package com.shop.demo.service;

import com.shop.demo.pojo.Orders;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface OrdersService {
    public int add(Orders orders);
    public List<Orders> select(String openid,int ispay, int receive,String productid);
    public int updateIsPay(String openid,String productid);
    public int updateReceice(String openid,String productid);
    public int updateReceiceByMan(String openid,String productid);
    public int cancel(String openid,String productid);
}
