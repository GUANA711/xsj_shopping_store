package com.shop.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.shop.demo.dto.BuycarDto;
import com.shop.demo.dto.GoodsTypeProduct;
import com.shop.demo.dto.OdersDto;
import com.shop.demo.pojo.*;
import com.shop.demo.dto.ProductDetailDto;
import com.shop.demo.service.LogServive;
import com.shop.demo.service.WXDataService;
import com.shop.demo.utiles.ResultInfoList;
import com.shop.demo.utiles.ResultListTotal;
import com.shop.demo.utiles.Status_Alice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
	@PostMapping("/addBuyCar")
	@ResponseBody
	public Status_Alice addBuyCar(@RequestBody JSONObject json) {

		//从前端获取数据
		String productid=json.getString("productid");
		String openid=json.getString("openid");

		Buycar buycar = new Buycar();
		Status_Alice status_alice=new Status_Alice();

		if (productid != null) {
			buycar.setProductid(productid);
		}
		if (openid != null) {
			buycar.setOpenid(openid);
		}
		try {
			int i=service.insert(buycar);
			if(i>0){
				status_alice.setMsg("添加成功");
				status_alice.setStatus(true);
			}else {
				status_alice.setMsg("添加失败");
			}
		}catch (Exception e){
			e.printStackTrace();
			status_alice.setMsg("失败");
		}
		return status_alice;
	}


	/**
	 * 根据openid查询购物车
	 */

	@RequestMapping("/selectByopenid")
	@ResponseBody
	public List<BuycarDto> selectByopenid(@RequestBody JSONObject json){

		//从前端获取数据
		String openid=json.getString("openid");

//		Map<String, Object> map = new HashMap<String, Object>();
		return service.selectByopenid(openid);
	}

	/**
	 * 移除购物车
	 * @return
	 */
	@RequestMapping("/deleteBuyCar")
	@ResponseBody
	public Status_Alice deleteBuyCar(@RequestBody JSONObject json){

		//从前端获取数据
		String id=json.getString("id");
		String openid=json.getString("openid");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id",id);
		map.put("openid",openid);

		Status_Alice status_alice=new Status_Alice();

		try {
			int i=service.deleteone(map);
			if(i>0){
				status_alice.setMsg("删除成功");
				status_alice.setStatus(true);
			}else {
				status_alice.setMsg("删除失败");
			}
		}catch (Exception e){
			e.printStackTrace();
			status_alice.setMsg("删除失败");
		}
		return status_alice;
	}

	/**
	 * 清空购物车
	 */
	@RequestMapping("/clearBuyCar")
	@ResponseBody
	public Status_Alice clearBuyCar(@RequestBody JSONObject json){

		String openid=json.getString("openid");

		Status_Alice status_alice=new Status_Alice();

		try {
			int i=service.deleteByopenid(openid);
			if(i>0){
				status_alice.setMsg("清空成功");
				status_alice.setStatus(true);
			}else {
				status_alice.setMsg("清空失败");
			}
		}catch (Exception e){
			e.printStackTrace();
			status_alice.setMsg("购物车已为空，清空失败");
		}
		return status_alice;
	}



    /**
     * 统计用户
     */
    @RequestMapping("/selectAllCustomer")
    @ResponseBody
    public ResultListTotal selectAllCustomer(){
        ResultListTotal resultListTotal=new ResultListTotal();
        resultListTotal.setTotal(service.selectAllCustomer().size());
        resultListTotal.setSelectList(service.selectAllCustomer());
        return resultListTotal;
    }

	/**
	 * 订单统计
	 */
	@RequestMapping("/selectAllOrder")
	@ResponseBody
	public ResultListTotal selectAllOrder(){
        ResultListTotal resultListTotal=new ResultListTotal();
        resultListTotal.setTotal(service.selectAllOrder().size());
        resultListTotal.setSelectList(service.selectAllOrder());
        return resultListTotal;
	}


	/**
	 * 商品统计
	 */
	@RequestMapping("/selectAllProduct")
	@ResponseBody
	public ResultListTotal selectAllProduct(){
        ResultListTotal resultListTotal=new ResultListTotal();
        resultListTotal.setTotal(service.selectAllProduct().size());
        resultListTotal.setSelectList(service.selectAllProduct());
        return resultListTotal;
	}

}
