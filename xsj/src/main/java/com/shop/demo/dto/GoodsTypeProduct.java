package com.shop.demo.dto;

import com.shop.demo.pojo.Goodstype;
import com.shop.demo.pojo.Product;

import java.util.List;


/**
 * 商品分类显示的dto
 *
 * @author Alice
 */
public class GoodsTypeProduct extends Goodstype {

    private List<Product> productlist;

    public List<Product> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<Product> productlist) {
        this.productlist = productlist;
    }
}