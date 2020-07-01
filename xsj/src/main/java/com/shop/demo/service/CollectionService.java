package com.shop.demo.service;

import com.shop.demo.dto.CollectionDetail;
import com.shop.demo.pojo.Collection;

import javax.annotation.Resource;
import java.util.List;

/*
* 购物车服务接口
*
* 实现加入收藏、查询收藏、移除收藏
* */
@Resource
public interface CollectionService {
    public int addCollection(Collection collection);
    public List<CollectionDetail> selectCollection(String openid);
    public int deleteCollection(String productid, String openid);
}
