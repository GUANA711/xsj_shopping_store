package com.shop.demo.dao;

import com.shop.demo.pojo.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Alice
 */
@Mapper
@Repository
public interface LogMapper {

    void addLog(Log log);

    String selectnickname(String openid);

    List<Log> showLog();

    List<Log> searchLog(Log log);

}
