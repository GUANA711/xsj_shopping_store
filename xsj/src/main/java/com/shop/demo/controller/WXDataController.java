package com.shop.demo.controller;

import java.util.List;

import com.shop.demo.dto.GoodsTypeProduct;
import com.shop.demo.pojo.Goodstype;
import com.shop.demo.pojo.Product;
import com.shop.demo.dto.ProductDetailDto;
import com.shop.demo.service.WXDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 微信数据接口controller层
 *
 * @author Alice
 */
@Controller
@ResponseBody
@RequestMapping("/wx")
public class WXDataController {

	@Autowired
	private WXDataService service;
	
	/**
	 * 显示首页商品列表
	 * cmd=recommend:精选推荐
	 * cmd=oldest：最新产品
	 * cmd=hot：热销产品
	 * filed=1：表示商品没有下架，可以显示
	 * @param cmd
	 * @return
	 */
	@RequestMapping("/index/{cmd}")
	@ResponseBody
	public List<Product> selectIndexProduct(@PathVariable("cmd")String cmd){
		List<Product> list =null;
		if(cmd.equals("recommend")){
			list = service.selectrecommendList();
		}else if(cmd.equals("oldest")){
			list = service.selectoldestList();
		}else if(cmd.equals("hot")){
			list = service.selecthotList();
		}
		return list;
	}
	
	/**
	 * 显示分类商品列表
	 * @return
	 */
	@RequestMapping("/product")
	@ResponseBody
	public List<GoodsTypeProduct> selectGoodsTypeProduct(){
		return service.selectGoodsTypeProduct();
	}

	
	/**
	 * 	显示商品详情的service
	 * 1、根据id查询商品的详细信息
	 * 2、根据id查询商品的图片列表
	 * @return
	 */
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public ProductDetailDto selectProductDetails(@PathVariable("id")String id){
		return service.selectProductDetails(id);
	}

	/**
	 * 加入购物车
	 */

	/**
	 * 查询购物车
	 */

	/**
	 * 移除购物车
	 */

	
}
