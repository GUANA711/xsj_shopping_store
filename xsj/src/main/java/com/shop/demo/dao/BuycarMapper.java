package com.shop.demo.dao;

import com.shop.demo.pojo.Buycar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BuycarMapper {
    int deleteByPrimaryKey(int id);

    int insert(Buycar record);

    int insertSelective(Buycar record);

    Buycar selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Buycar record);

    int updateByPrimaryKey(Buycar record);

    List<Buycar> selectByopenid (String openid);
}