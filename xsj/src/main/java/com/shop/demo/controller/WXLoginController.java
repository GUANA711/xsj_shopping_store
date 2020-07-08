package com.shop.demo.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.demo.dao.MyLog;
import com.shop.demo.pojo.Customer;
import com.shop.demo.service.WXDataService;
import com.shop.demo.utiles.Status_Alice;
import jdk.internal.dynalink.beans.StaticClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 微信授权登录controller
 * 
 * @author Alice
 */
@Controller
@ResponseBody
@RequestMapping("/wxlogin")
public class WXLoginController {

	@Autowired
	private WXDataService service;


	static String openid;
	/**
	 * 微信授权登录接口 
	 * 1、使用code与微信交换得到openid 
	 * 2、判断保存openid与nickname到数据库（需要先判断是否已经保存）
	 * @param code
	 *            微信登录的code值（每次获取到的code不一样）
	 * @param nickname
	 *            用户的昵称
	 * @return openid。如果注册不成功，返回空字符串
	 */

    @MyLog("用户登录")
	@RequestMapping("/login")
	@ResponseBody
	public String login(String code, String nickname) {
		openid = getopenid(code);
		//判断数据库中是否有openid存在
		Customer cus = service.selectCustomerById(openid);
		if(cus==null){
			//表示数据库中没有这个用户，插入这个用户
			cus = new Customer();
			cus.setOpenid(openid);
			cus.setNickname(nickname);
			int rtn = service.insertCustomer(cus);
			if(rtn>0){
				
			}else{
				openid="";
			}
		}
		return openid;
	}

	/**
	 * 使用code从微信端交换openid
	 * 
	 * @param code
	 * @return
	 */
	public String getopenid(String code) {
		String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=wxc5d2242a2cb10217&secret=618dacb56e78908973e09ed2dcc2abcb&js_code="
				+ code + "&grant_type=authorization_code";
		//获取到的是一个json字符串。
		String rtnvalue = GET(WX_URL);
		//解析json字符串，得到openid
		ObjectMapper mapper = new ObjectMapper();
		String openid = "";
		try {
			Map<String, String> map = mapper.readValue(rtnvalue, Map.class);
			openid= map.get("openid");
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return openid;
	}

	/**
	 * 发起get请求的方法。
	 * @param url
	 * @return
	 */
	public String GET(String url) {
		String result = "";
		BufferedReader in = null;
		InputStream is = null;
		InputStreamReader isr = null;
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.connect();
			Map<String, List<String>> map = conn.getHeaderFields();
			is = conn.getInputStream();
			isr = new InputStreamReader(is);
			in = new BufferedReader(isr);
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			// 异常记录
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (is != null) {
					is.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (Exception e2) {
				// 异常记录
			}
		}
		return result;
	}
}
