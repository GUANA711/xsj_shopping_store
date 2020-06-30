package com.shop.demo.service;

import com.shop.demo.pojo.Goodstype;

import java.util.List;


/**
 * 商品类型管理servcie层接口
 *
 *
 * @author Alice
 */
public interface GoodsTypeService {

	/**
	 * 根据id查询一条商品类型的详细信息
	 * @param id
	 * @return
	 */
	Goodstype selectGoodsTypeById(String id);

}
