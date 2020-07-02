package com.shop.demo.dao;

import com.shop.demo.dto.CollectionDetail;
import com.shop.demo.pojo.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    //查询收藏
    @Select("select collecttime,productid,name,price,stock,number,pthoto from product,collection where collection.productid = product.id and collection.openid = #{openid}")
    List<CollectionDetail> select(String openid);

    //移除收藏
    @Delete("delete from collection where productid = #{productid} and openid = #{openid}")
    int delete(String productid,String openid);
}