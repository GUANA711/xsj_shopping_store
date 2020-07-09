package com.shop.demo.dao;

import com.shop.demo.pojo.Buycar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BuycarMapper {
    int deleteone(Map<String,Object> map);

    int insert(Buycar record);

    int insertSelective(Buycar record);

    Buycar selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Buycar record);

    int updateByPrimaryKey(Buycar record);

    List<Buycar> selectByopenid (String openid);

    int deleteByopenid(String openid);
}