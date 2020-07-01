package com.shop.demo.dto;

import com.shop.demo.pojo.Product;
import com.shop.demo.pojo.Productimgs;

import java.util.List;

/**
 * 前端显示的商品详情数据传输对象
 *
 * @author Alice
 */
public class ProductDetailDto {

    private Product product;
    private List<Productimgs> imgs;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Productimgs> getImgs() {
        return imgs;
    }

    public void setImgs(List<Productimgs> imgs) {
        this.imgs = imgs;
    }
}
