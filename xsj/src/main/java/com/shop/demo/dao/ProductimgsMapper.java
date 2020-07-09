package com.shop.demo.dao;

import com.shop.demo.pojo.Productimgs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductimgsMapper {
    int deleteByPrimaryKey(String imgid);

    int insert(Productimgs record);

    int insertSelective(Productimgs record);

    Productimgs selectByPrimaryKey(String imgid);

    int updateByPrimaryKeySelective(Productimgs record);

    int updateByPrimaryKey(Productimgs record);

    /**
     *
     * @param productid
     * @return
     */
    @Select("select * from productimgs where productid=#{productid}")
    List<Productimgs> selectByproductid(String productid);


    /**
     * productid查询imgurl
     */
    List<Productimgs> selectBypid(String productid);
}