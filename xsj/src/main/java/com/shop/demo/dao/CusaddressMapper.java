package com.shop.demo.dao;

import com.shop.demo.pojo.Cusaddress;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CusaddressMapper {
    int deleteByPrimaryKey(String id);

    int insert(Cusaddress record);

    int insertSelective(Cusaddress record);

    Cusaddress selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Cusaddress record);

    int updateByPrimaryKey(Cusaddress record);

    @Select("select * from cusaddress where openid = #{openid}")
    List<Cusaddress> selectByOpenid(String openid);

    @Delete("delete from cusaddress where id = #{id}")
    int delete(String id);

    int update(@Param("id") String id,@Param("openid") String openid,@Param("address") String address,@Param("isdefault") int isdefault,@Param("phone") String phone,@Param("title") String title);


}