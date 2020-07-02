package com.shop.demo.service;

import com.shop.demo.dto.OdersDto;
import com.shop.demo.pojo.Orders;

import java.util.List;

public interface OdersService {
    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<OdersDto> selectBySelective(Orders orders);
}
