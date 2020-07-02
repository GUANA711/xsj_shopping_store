package com.shop.demo.dao;

import com.shop.demo.dto.OdersDto;
import com.shop.demo.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<OdersDto> selectBySelective(Orders orders);

    List<Orders> selectAllOrder();
}