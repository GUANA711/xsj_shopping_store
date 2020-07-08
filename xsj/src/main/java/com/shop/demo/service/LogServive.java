package com.shop.demo.service;

import com.shop.demo.pojo.Log;
import java.util.List;
import java.util.Map;

/**
 * @author Alice
 */
public interface LogServive {
    void addLog(Log log);
    String selectnickname(String openid);


}
