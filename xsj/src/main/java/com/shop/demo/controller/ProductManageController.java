package com.shop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.demo.pojo.Product;
import com.shop.demo.service.ProductService;
import com.shop.demo.utiles.Status_guana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/product")
public class ProductManageController {
    @Autowired
    ProductService productService;

    @PostMapping("/show")
    public List<Product> show(){
        return  productService.show();
    }

    @PostMapping("/add")
    public Status_guana add(@RequestBody JSONObject json){
        Status_guana status_guana=new Status_guana();
        Product product =new Product();
        String name=json.getString("name");
        String price=json.getString("price");
        String title=json.getString("title");
        String stock=json.getString("stock");
        String description=json.getString("description");
        String recommend=json.getString("recommend");
        String oldest=json.getString("oldest");
        String hot=json.getString("hot");
        String typename=json.getString("typename");

        String id= UUID.randomUUID().toString();


        return  status_guana;
    }
}
