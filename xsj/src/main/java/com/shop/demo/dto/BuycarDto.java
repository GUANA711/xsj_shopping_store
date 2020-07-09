package com.shop.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alice
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuycarDto {
    private String id;
    private String productid;
    private String name;
    private String openid;
    private Double price;
    private Integer stock;
    private Integer number;
    private String imgurl;
    private int shopNum=1;//用户想购买的数量
}
