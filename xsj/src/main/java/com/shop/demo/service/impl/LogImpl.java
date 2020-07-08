package com.shop.demo.service.impl;

import com.shop.demo.dao.LogMapper;
import com.shop.demo.pojo.Log;
import com.shop.demo.service.LogServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Alice
 */
@Service
public class LogImpl implements LogServive {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void addLog(Log log) {
        logMapper.addLog(log);
    }

    @Override
    public String selectnickname(String openid) {
        return logMapper.selectnickname(openid);
    }

}
