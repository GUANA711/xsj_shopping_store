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
	private ProductMapper pmapper;

	private CustomerMapper cusmapper;
	@Autowired
	private GoodstypeMapper gtmapper;

	private ProductimgsMapper imgmapper;

	private CusaddressMapper addrmapper;
	@Override
	public List<Product> selectProductList(Product p) {
		return pmapper.selectProductList(p);
	}
	@Override
	public Customer selectCustomerById(String openid) {
		return cusmapper.selectByPrimaryKey(openid);
	}
	@Override
	public int insertCustomer(Customer cus) {
		return cusmapper.insert(cus);
	}

	@Override
	public List<GoodsTypeProduct> selectGoodsTypeProduct() {
		//查询所有可用的商品分类列表
		Goodstype gt = new Goodstype();
		gt.setState(1);
		List<Goodstype> gtlist = (List<Goodstype>) gtmapper.selectGoodsTypeList(gt);
		//查询每一个分类中的商品,然后再将其保存到goodstypeproduct对象中
		List<GoodsTypeProduct> list = new ArrayList<>();
		Product p = new Product();
		p.setFields1("1");
		for(Goodstype type : gtlist){
			p.setTypeid(type.getId());
			List<Product> plist = pmapper.selectProductList(p);
			GoodsTypeProduct gtp = new GoodsTypeProduct();
			gtp.setId(type.getId());
			gtp.setName(type.getName());
			gtp.setProductlist(plist);
			list.add(gtp);
		}	
		return list;
	}
	@Override
	public ProductDetailDto selectProductDetails(String pid) {
		//根据id查询商品详情
		Product p = pmapper.selectByPrimaryKey(pid);
		//根据商品id查询商品的图片列表
		List<Productimgs> imgs = imgmapper.selectByPid(pid);
		//整合返回值
		ProductDetailDto dto = new ProductDetailDto();
		dto.setProduct(p);
		dto.setImgs(imgs);
		return dto;
	}

}
