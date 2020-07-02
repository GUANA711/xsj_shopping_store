package com.shop.demo.dto;

import com.shop.demo.pojo.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GUANA
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OdersDto extends Orders {
    private String  pthoto;
    private int stock;
}
