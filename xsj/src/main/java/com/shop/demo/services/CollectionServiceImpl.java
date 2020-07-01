package com.shop.demo.services;

import com.shop.demo.dao.CollectionMapper;
import com.shop.demo.pojo.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    public CollectionMapper collectionMapper;
    @Override
    public int addCollection(Collection collection) {
        return collectionMapper.insert(collection);
    }

    @Override
    public List<Collection> selectCollection() {
        return collectionMapper.select();
    }

    @Override
    public int deleteCollection(String productid,String openid) {
        return collectionMapper.delete(productid,openid);
    }
}
