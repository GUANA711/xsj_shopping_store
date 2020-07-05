package com.shop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.demo.pojo.Orders;
import com.shop.demo.service.OrdersService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(allowCredentials = "true",allowedHeaders = "*" , origins = "*")
@RestController
public class OrdersController {
    @Autowired
    public OrdersService ordersService;

    @PostMapping("/orders/add")
    public Map<String,Object> add(@RequestBody JSONObject json){
        Map<String,Object> map = new HashMap<>();
        Orders orders = new Orders();

        orders.setId(UUID.randomUUID().toString());

        String openid = json.getString("openid");
        String productid = json.getString("pId");
        String productname = json.getString("pName");
        String title = json.getString("title");
        Double price = Double.parseDouble(json.getString("price"));
        Integer number = Integer.parseInt(json.getString("number"));
        Double totle = price*number;
        Integer ispay = Integer.parseInt(json.getString("ispay"));
        String invoice = json.getString("invoice");
        String address = json.getString("address");
        String addrid = json.getString("addrid");

        orders.setOpenid(openid);
        orders.setProductid(productid);
        orders.setProductname(productname);
        orders.setTitle(title);
        orders.setPrice(price);
        orders.setNumber(number);
        orders.setTotle(totle);
        orders.setIspay(ispay);
        orders.setInvoice(invoice);
        orders.setAddress(address);
        orders.setAddrid(addrid);
        orders.setState(1);
        orders.setReceive(2);
        try {
            int res = ordersService.add(orders);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","添加成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","添加失败");
        }
        return map;
    }

    @GetMapping("/orders/select")
    public Map<String,Object> selectByNoPay( @RequestBody JSONObject json){
        Map<String,Object> map = new HashMap<>();
        String openid = json.getString("openid");
        String ispayS = json.getString("ispayS");
        String receiveS = json.getString("receiveS");
        String productid =json.getString("productid");
        int ispay;
        int receive;
        if(ispayS == null){
            ispay = -1;
        }else {
            ispay = Integer.parseInt(ispayS);
        }
        if (receiveS == null){
            receive = -1;
        }else {
            receive = Integer.parseInt(receiveS);
        }
        try {
            List<Orders> res = ordersService.select(openid,ispay,receive,productid);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","查询成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","查询失败");
        }
        return map;
    }

    /*去收货*/
    @PostMapping("/orders/update/receive")
    public Map<String,Object> updateReceive(@RequestParam("openid") String openid,
                                            @RequestParam("productid") String productid){
        Map<String,Object> map = new HashMap<>();
        try {
            int res = ordersService.updateReceice(openid,productid);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","收货成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","收货失败");
        }
        return map;
    }

    /*去发货*/
    @PostMapping("/orders/update/delivery")
    public Map<String,Object> updateReceiveByMan(@RequestParam("openid") String openid,
                                                 @RequestParam("productid") String productid){
        Map<String,Object> map = new HashMap<>();
        try {
            int res = ordersService.updateReceiceByMan(openid,productid);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","发货成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","发货失败");
        }
        return  map;
    }

    /*去支付*/
    @PostMapping("/orders/update/pay")
    public Map<String,Object> updatePay(@RequestParam("openid") String openid,
                                        @RequestParam("productid") String productid){
        Map<String,Object> map = new HashMap<>();
        try {
            int res = ordersService.updateIsPay(openid,productid);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","支付成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","支付失败");
        }
        return  map;
    }

    @PostMapping("/orders/cancel")
    public Map<String,Object> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("productid") String productid){
        Map<String,Object> map = new HashMap<>();
        try {
            int res = ordersService.cancel(openid,productid);
            map.put("status",200);
            map.put("info",res);
            map.put("msg","取消成功");
        }catch (SqlSessionException e){
            map.put("status",500);
            map.put("msg","取消失败");
        }
        return  map;
    }
}
