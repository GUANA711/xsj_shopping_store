package com.shop.demo.service;

import com.shop.demo.pojo.Goodstype;
<<<<<<< HEAD

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

=======
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsTypeService {
    int deleteByPrimaryKey(String id);

    int insert(Goodstype record);

    int insertSelective(Goodstype record);

    Goodstype selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goodstype record);

    int updateByPrimaryKey(Goodstype record);

    List<Goodstype> showAll();
>>>>>>> bb8ad07087a864b3e44fdce70c98ec26dc30daa5
}
