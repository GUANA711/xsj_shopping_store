package com.shop.demo.dao;

import com.shop.demo.pojo.Cusaddress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CusaddressMapper {
    int deleteByPrimaryKey(String id);

    int insert(Cusaddress record);

    int insertSelective(Cusaddress record);

    Cusaddress selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Cusaddress record);

    int updateByPrimaryKey(Cusaddress record);
}