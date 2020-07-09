package com.shop.demo.dao;

import com.shop.demo.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    @Select("select * from product")
    List<Product> show();

    List<Product> selectBySelective(Product record);

    /**
     * 首页商品显示
     * @return
     */
    List<Product> selectrecommendList();
    List<Product> selectoldestList();
    List<Product> selecthotList();

    /**
     * 查询某一类所有的商品
     */
    List<Product> selectOneList(String id);

    /*增加库存*/
    @Update("update product set stock = stock+1 where id = #{id}")
    int addStock(String id);

    /*减少库存*/
    @Update("update product set stock = stock-1 where id = #{id}")
    int decreaseStock(String id);

    @Select("select stock from product where id = #{id}")
    int selectStock(String id);

}