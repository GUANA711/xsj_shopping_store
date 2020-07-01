package com.shop.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.shop.demo.dao.*;
import com.shop.demo.dto.GoodsTypeProduct;
import com.shop.demo.dto.ProductDetailDto;
import com.shop.demo.pojo.*;
import com.shop.demo.service.WXDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 微信数据接口服务层实现类
 *
 * @author Alice
 */
@Service
public class WXDataServiceImpl implements WXDataService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private GoodstypeMapper goodstypeMapper;

	@Autowired
	private ProductimgsMapper productimgsMapper;


	/**
	 * 登录后保存用户信息
	 * @param cus
	 * @return
	 */
	@Override
	public int insertCustomer(Customer cus) {
		return customerMapper.insert(cus);
	}

	/**
	 * 根据openid查询用户详细信息
	 * @param openid
	 * @return
	 */
	@Override
	public Customer selectCustomerById(String openid) {
		return customerMapper.selectByPrimaryKey(openid);
	}


	/**
	 * 显示首页商品列表（主要用于首页的精选推荐，最新产品，热销产品的查询）
	 * @param p
	 * @return
	 */
	@Override
	public List<Product> selectIndexProduct(Product p) {
		return productMapper.selectProductList(p);
	}

	/**
	 * 显示分类商品列表
	 * @return
	 */
	@Override
	public List<GoodsTypeProduct> selectGoodsTypeProduct() {
		//查询所有可用的商品分类列表
		Goodstype gt = new Goodstype();
		gt.setState(1);
		List<Goodstype> gtlist =  goodstypeMapper.showGoodsTypeList(gt);
		//查询每一个分类中的商品,然后再将其保存到goodstypeproduct对象中
		List<GoodsTypeProduct> list = new ArrayList<>();
		Product p = new Product();
		p.setFields("1");
		for(Goodstype type : gtlist){
			p.setTypeid(type.getId());
			List<Product> plist = productMapper.selectProductList(p);
			GoodsTypeProduct gtp = new GoodsTypeProduct();
			gtp.setId(type.getId());
			gtp.setName(type.getName());
			gtp.setProductlist(plist);
			list.add(gtp);
		}	
		return list;
	}

	/**
	 * 显示商品详情的service
	 *  1、根据id查询商品的详细信息
	 * 	2、根据id查询商品的图片列表
	 * @param id
	 * @return
	 */
	@Override
	public ProductDetailDto selectProductDetails(String id) {
		//根据商品id查询商品详情
		Product p = productMapper.selectByPrimaryKey(id);
		//根据商品id查询商品的图片列表
		List<Productimgs> imgs = productimgsMapper.selectByproductid(id);
		//整合返回值
		ProductDetailDto dto = new ProductDetailDto();
		dto.setProduct(p);
		dto.setImgs(imgs);
		return dto;
	}


}
