package com.shop.demo.service;

import java.util.List;

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
	 * @param p
	 * @return
	 */
	List<Product> selectIndexProduct(Product p);

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

}
