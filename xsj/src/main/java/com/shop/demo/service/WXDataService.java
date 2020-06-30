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
	 * 查询商品列表（主要用于首页的精选推荐，最新产品，热销产品的查询）
	 * @param p
	 * @return
	 */
	List<Product> selectProductList(Product p);
	/**
	 * 根据openid查询用户详细信息
	 * @param openid
	 * @return
	 */
	Customer selectCustomerById(String openid);
	/**
	 * 保存客户信息
	 * @param cus
	 * @return
	 */
	int insertCustomer(Customer cus);
	/**
	 * 查询分类商品列表
	 * @return
	 */
	List<GoodsTypeProduct> selectGoodsTypeProduct();
	
	/**
	 * 查询商品详情的service
	 * 1、根据id查询商品的详细信息
	 * 2、根据id查询商品的图片列表
	 * @param pid
	 * @return
	 */
	ProductDetailDto selectProductDetails(String pid);

	/**
	 * 根据id查询一条商品类型的详细信息
	 * @param id
	 * @return
	 */
	Goodstype selectGoodsTypeById(String id);
}
