package com.shop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.demo.pojo.Log;
import com.shop.demo.service.LogServive;
import com.shop.demo.utiles.ResultListTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alice
 */

@Controller
@ResponseBody
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogServive logServive;

    /**
     * 分页显示日志
     */
    @GetMapping("/showLog")
    public ResultListTotal showLog(@RequestParam("page") int page,@RequestParam("limit") int limit){
        ResultListTotal resultListTotal=new ResultListTotal();
        PageHelper.startPage(page,limit);
        List<Log> logs=logServive.showLog();
        PageInfo<Log> info=new PageInfo<>(logs);
        resultListTotal.setCode(0);
        resultListTotal.setTotal(info.getTotal());
        resultListTotal.setSelectList(logs);
        return resultListTotal;
    }

    /**
     * 分页   模糊搜索日志
     */
    @GetMapping("/searchLog")
    public ResultListTotal searchLog(@RequestBody JSONObject json, @RequestParam("page") int page, @RequestParam("limit") int limit){

//        String logid = json.getString("logid");
        String openid = json.getString("openid");
        String nickname = json.getString("nickname");
        String Operation = json.getString("Operation");
        String Method = json.getString("Method");
        String IP = json.getString("IP");
        String Create_time = json.getString("Create_time");

        Log log=new Log();
//        log.setLogid(Integer.parseInt(logid));
        log.setOpenid(openid);
        log.setNickname(nickname);
        log.setOperation(Operation);
        log.setMethod(Method);
        log.setIP(IP);
        log.setCreate_time(Create_time);

        ResultListTotal resultListTotal=new ResultListTotal();
        PageHelper.startPage(page,limit);
        List<Log> logs=logServive.searchLog(log);
        PageInfo<Log> info=new PageInfo<>(logs);
        resultListTotal.setCode(0);
        resultListTotal.setTotal(info.getTotal());
        resultListTotal.setSelectList(logs);
        return resultListTotal;
    }
}
