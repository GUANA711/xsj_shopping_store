package com.shop.demo.dao;

import com.shop.demo.pojo.Goodstype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodstypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Goodstype record);

    int insertSelective(Goodstype record);

    Goodstype selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goodstype record);

    int updateByPrimaryKey(Goodstype record);

    @Select("select * from goodstype")
    List<Goodstype> showAll();

    @Select("select * from goodstype where name=#{name}")
    Goodstype selectByname(String name);

    /**
     * 显示商品类型的列表
     * @param
     * @return
     */
    List<Goodstype> showGoodsTypeList();

}