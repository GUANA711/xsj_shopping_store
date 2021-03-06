package com.shop.demo.service;

import java.util.List;
import java.util.Map;

import com.shop.demo.dto.BuycarDto;
import com.shop.demo.dto.GoodsTypeProduct;
import com.shop.demo.dto.ProductDetailDto;
import com.shop.demo.pojo.*;
/**
 * 微信数据接口服务层
 *
 * @author Alice
 */
public interface WXDataService {

	/**
	 * 登录后保存用户信息
	 * @param cus
	 * @return
	 */
	int insertCustomer(Customer cus);


	/**
	 * 根据openid查询用户详细信息
	 * @param openid
	 * @return
	 */
	Customer selectCustomerById(String openid);


	/**
	 * 显示商品列表（主要用于首页的精选推荐，最新产品，热销产品的查询）
	 * @param
	 * @return
	 */
	List<Product> selectrecommendList();
	List<Product> selectoldestList();
	List<Product> selecthotList();



	/**
	 * 查询某一类所有的商品
	 */
	List<Product> selectOneList(String id);


	/**
	 * 显示分类商品列表
	 * @return
	 */
	List<GoodsTypeProduct> selectGoodsTypeProduct();
	
	/**
	 * 显示商品详情的service
	 * 1、根据id查询商品的详细信息
	 * 2、根据id查询商品的图片列表
	 * @param id
	 * @return
	 */
	ProductDetailDto selectProductDetails(String id);

	/**
	 * 添加购物车
	 * @param record
	 * @return
	 */
	int insert(Buycar record);

	/**
	 * 根据openid查询购物车
	 */
	List<BuycarDto> selectByopenid (String openid);

	/**
	 * 查询某一个商品的库存
	 */
	int selectshopNum(String productid);
    String selectpid(String openid);


	/**
	 * 移除购物车
	 */
	int deleteone(Map<String,Object> map);


	/**
	 * 清空购物车
	 */
	int deleteByopenid(String openid);

	/**
	 * 统计用户
	 */
	List<Customer> selectAllCustomer ();

	/**
	 * 订单统计
	 */
	List<Orders> selectAllOrder ();

	/**
	 * 商品统计
	 */
	List<Product> selectAllProduct();

}
