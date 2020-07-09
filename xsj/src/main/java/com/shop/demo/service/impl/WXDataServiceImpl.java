package com.shop.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shop.demo.dao.*;
import com.shop.demo.dto.BuycarDto;
import com.shop.demo.dto.GoodsTypeProduct;
import com.shop.demo.dto.ProductDetailDto;
import com.shop.demo.pojo.*;
import com.shop.demo.service.WXDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 微信数据接口服务层实现类
 *
 * @author Alice
 */
@Service
@Transactional
public class WXDataServiceImpl implements WXDataService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private GoodstypeMapper goodstypeMapper;

	@Autowired
	private ProductimgsMapper productimgsMapper;

	@Autowired
    private BuycarMapper buycarMapper;

	@Autowired
	private OrdersMapper ordersMapper;

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
	 * @return
	 */
	@Override
	public List<Product> selectrecommendList() {
		return productMapper.selectrecommendList();
	}
	@Override
	public List<Product> selectoldestList() {
		return productMapper.selectoldestList();
	}
	@Override
	public List<Product> selecthotList() {
		return productMapper.selecthotList();
	}


	/**
	 * 查询某一类所有的商品
	 */
	@Override
	public List<Product> selectOneList(String id) {
		return productMapper.selectOneList(id);
	}

	/**
	 * 显示分类商品列表
	 * @return
	 */
	@Override
	public List<GoodsTypeProduct> selectGoodsTypeProduct() {
		//查询所有可用的商品分类列表
//		Goodstype gt = new Goodstype();
		List<Goodstype> gtlist =  goodstypeMapper.showGoodsTypeList();
		//查询每一个分类中的商品selectOneList(type.getId())
		// 然后再将其保存到goodstypeproduct对象中
		List<GoodsTypeProduct> list = new ArrayList<>();
		for(Goodstype type : gtlist) {
			List<Product> plist = productMapper.selectOneList(type.getId());
			GoodsTypeProduct gtp = new GoodsTypeProduct();
			gtp.setId(type.getId());
			gtp.setName(type.getName());
			gtp.setState(type.getState());
			gtp.setListId(type.getListId());
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
		//根据商品id查询商品的图片url列表
		List<Productimgs> imgs = productimgsMapper.selectBypid(id);
		//整合返回值
		ProductDetailDto dto = new ProductDetailDto();
		dto.setProduct(p);
		dto.setImgs(imgs);
		return dto;
	}

    /**
     * 添加购物车
     * @param record
     * @return
     */
    @Override
    public int insert(Buycar record){
        return buycarMapper.insert(record);
    }

	/**
	 * 根据openid查询购物车
	 */
	@Override
	 public List<BuycarDto> selectByopenid (String openid){
		return buycarMapper.selectByopenid(openid);
	}

	/**
	 *
	 */

	@Override
	public int selectshopNum(String productid){
		return ordersMapper.selectshopNum(productid);
	}
    @Override
    public String selectpid(String openid){
        return buycarMapper.selectpid(openid);
    }
	/**
	 * 移除购物车
	 */
	@Override
	public int deleteone(Map<String,Object> map){
		return buycarMapper.deleteone(map);
	}

	/**
	 * 清空购物车
	 */
	@Override
	public int deleteByopenid(String openid){
		return buycarMapper.deleteByopenid(openid);
	}


	/**
	 * 统计用户
	 */
	@Override
	public List<Customer> selectAllCustomer (){
		return customerMapper.selectAllCustomer();
	}

	/**
	 * 订单统计
	 */
	@Override
	public List<Orders> selectAllOrder (){
		return ordersMapper.selectAllOrder();
	}

	/**
	 * 商品统计
	 */
	@Override
	public List<Product> selectAllProduct (){
		return productMapper.show();
	}
}

