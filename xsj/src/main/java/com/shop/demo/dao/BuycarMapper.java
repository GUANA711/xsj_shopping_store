package com.shop.demo.dao;

import com.shop.demo.dto.BuycarDto;
import com.shop.demo.pojo.Buycar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

    @Select("select b.id, b.productid, b.openid,p.name,p.price,p.stock,p.number,pi.imgurl\n" +
            "    from product p,buycar b,productimgs pi\n" +
            "    where pi.productid=p.id and p.id=b.productid and b.openid = #{openid} group by b.id")
    List<BuycarDto> selectByopenid(String openid);

    int deleteByopenid(String openid);

    String selectpid(String openid);
}