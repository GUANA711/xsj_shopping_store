package com.shop.demo.dao;

import com.shop.demo.dto.OdersDto;
import com.shop.demo.dto.OrdersDetail;
import com.shop.demo.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    /*查询订单
    * 查询所有订单
    * 查询待发货
    * 查询待收货订单
    * 查询待付款订单
    * */
    List<OrdersDetail> select(@Param("openid") String openid, @Param("ispay") int ispay, @Param("receive") int receive, @Param("productid") String productid);

    /*去支付*/
    @Update("update orders set ispay = 1,receive = 2 where id = #{id}")
    int updateIspay(String id);

    /*去发货*/
    @Update("update orders set receive = 0 where id = #{id}")
    int updateReceiceByMan(String id);

    /*去收货*/
    @Update("update orders set receive = 1, state = 3 where id = #{id}")
    int updateReceice(String id);

    /*取消订单*/
    @Update("update orders set state = 2 where id = #{id}")
    int cancel(String id);

    List<OdersDto> selectBySelective(Orders orders);

    List<Orders> selectAllOrder();
}