package com.shop.demo.dao;

import com.shop.demo.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomerMapper {
    int deleteByPrimaryKey(String openid);

    /**
     * 根据openid查询用户记录
     * @param record
     * @return
     */
    int insert(Customer record);

    int insertSelective(Customer record);

    /**
     * 保存用户记录到数据库
     * @param openid
     * @return
     */
    Customer selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}