package com.shop.demo.controller;

import java.util.List;

import com.shop.demo.dto.GoodsTypeProduct;
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
	 * @param cmd
	 * @return
	 */
	@RequestMapping("/home/{cmd}")
	@ResponseBody
	public List<Product> selectHomeProduct(@PathVariable("cmd")String cmd){
		Product p = new Product();
		if(cmd.equals("recommend")){
			p.setRecommend(1);
		}else if(cmd.equals("oldest")){
			p.setOldest(1);
		}else if(cmd.equals("hot")){
			p.setHot(1);
		}
		List<Product> list = service.showHomeProductList(p);
		return list;
	}
	
	/**
	 * 显示分类商品列表
	 * @return
	 */
	@RequestMapping("/product")
	@ResponseBody
	public List<GoodsTypeProduct> showTypeList(){
		return service.selectGoodsTypeProduct();
	}
	
	/**
	 * 显示商品详情的service
	 * 1、根据id查询商品的详细信息
	 * 2、根据id查询商品的图片列表
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public ProductDetailDto showProductDetails(@PathVariable("id")String id){
		return service.showProductDetails(id);
	}
	
}
