package com.shop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.demo.dto.OdersDto;
import com.shop.demo.pojo.Orders;
import com.shop.demo.pojo.Product;
import com.shop.demo.service.OdersService;
import com.shop.demo.utiles.ResultInfoList;
import com.shop.demo.utiles.Status_guana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 订单管路接口
 */
@Controller
@ResponseBody
@RequestMapping("/order")
public class OrderManageController {
     @Autowired
    OdersService odersService;


     @PostMapping("/update")
     public Status_guana update_insert(@RequestBody JSONObject json){
         Status_guana status_guana=new Status_guana();
         String id=json.getString("id");
         String openid=json.getString("openid");
         String productid=json.getString("productid");
         String productname=json.getString("productname");
         String title=json.getString("title");
         String price=json.getString("price");
         String number=json.getString("number");
         String totle=json.getString("totle");
         String ispay=json.getString("ispay");
         String invoice=json.getString("invoice");
         String receive=json.getString("receive");
         String state=json.getString("state");
         String address=json.getString("address");
         String addrid=json.getString("addrid");
         Orders orders=new Orders();

         orders.setId(id);
         orders.setOpenid(openid);
         orders.setProductid(productid);
         orders.setProductname(productname);
         orders.setTitle(title);
         orders.setInvoice(invoice);
         orders.setAddress(address);
         orders.setAddrid(addrid);

         if(price!=null){
             orders.setPrice(Double.parseDouble(price));
         }
         if(number!=null){
             orders.setNumber(Integer.parseInt(number));
         }
         if(ispay!=null){
             orders.setIspay(Integer.parseInt(ispay));
         }
         if(receive!=null){
             orders.setReceive(Integer.parseInt(receive));
         }
         if(state!=null){
             orders.setState(Integer.parseInt(state));
         }
         if(totle!=null){
             orders.setTotle(Double.parseDouble(totle));
         }
         try {
            int i=odersService.updateByPrimaryKeySelective(orders);
            if(i>0){
                status_guana.setMsg("更新成功");
                status_guana.setStatus(true);
            }else {
                status_guana.setMsg("更新失败");
            }
         }catch (Exception e){
             e.printStackTrace();
             status_guana.setMsg("更新失败");
             return status_guana;
         }
         return status_guana;
     }

    /**

     * 查询订单
     * @param
     * @return
     */
     @GetMapping("/select")
     public ResultInfoList select(@RequestParam(value="page",required=false) int page, @RequestParam(value="limit",required=false) int limit,@RequestParam("openid") String openid,@RequestParam("id") String id){

         Orders orders=new Orders();
         if(id.equals("")){
             id=null;
         }
         if(openid.equals("")){
             openid=null;
         }
         orders.setId(id);
         orders.setOpenid(openid);




         try {
             ResultInfoList resultInfoList=new ResultInfoList();
             PageHelper.startPage(page,limit);
             List<OdersDto> odersDtoList=odersService.selectBySelective(orders);
             PageInfo<OdersDto> info=new PageInfo<>(odersDtoList);
             resultInfoList.setTotal(info.getTotal());
             resultInfoList.setSelectList(odersDtoList);
             return resultInfoList;

         }catch (Exception e){
             e.printStackTrace();
             return null;
         }



     }

    /**
     * 待支付订单显示
     * @return
     */
     @GetMapping("/nopay_show")
    public ResultInfoList nopay(@RequestParam("page") int page,@RequestParam("limit") int limit){
         Orders orders=new Orders();
         orders.setIspay(0);
         orders.setState(1);
         orders.setReceive(2);

         ResultInfoList resultInfoList=new ResultInfoList();
         PageHelper.startPage(page,limit);
         List<OdersDto> odersDtoList=odersService.selectBySelective(orders);
         PageInfo<OdersDto> info=new PageInfo<>(odersDtoList);
         resultInfoList.setTotal(info.getTotal());
         resultInfoList.setSelectList(odersDtoList);
         return resultInfoList;

     }

    /***
     * 待发货显示
     * @return
     */
    @GetMapping("/noallcation_show")
    public ResultInfoList noallocation(@RequestParam("page") int page,@RequestParam("limit") int limit){
        Orders orders=new Orders();
        orders.setIspay(1);
        orders.setState(1);
        orders.setReceive(2);
        ResultInfoList resultInfoList=new ResultInfoList();
        PageHelper.startPage(page,limit);
        List<OdersDto> odersDtoList=odersService.selectBySelective(orders);
        PageInfo<OdersDto> info=new PageInfo<>(odersDtoList);
        resultInfoList.setTotal(info.getTotal());
        resultInfoList.setSelectList(odersDtoList);
        return resultInfoList;

    }

    /**
     * 待收货显示
     * @return
     */
    @GetMapping("/norecive_show")
    public ResultInfoList norecive(@RequestParam("page") int page,@RequestParam("limit") int limit){
        Orders orders=new Orders();
        orders.setIspay(1);
        orders.setState(1);
        orders.setReceive(0);
        ResultInfoList resultInfoList=new ResultInfoList();
        PageHelper.startPage(page,limit);
        List<OdersDto> odersDtoList=odersService.selectBySelective(orders);
        PageInfo<OdersDto> info=new PageInfo<>(odersDtoList);
        resultInfoList.setTotal(info.getTotal());
        resultInfoList.setSelectList(odersDtoList);
        return resultInfoList;
    }

    /**
     * 完成订单显示显示
     * @return
     */
    @GetMapping("/ok_show")
    public ResultInfoList nok_show(@RequestParam("page") int page,@RequestParam("limit") int limit){
        Orders orders=new Orders();
        orders.setIspay(1);
        orders.setState(3);
        orders.setReceive(1);
        ResultInfoList resultInfoList=new ResultInfoList();
        PageHelper.startPage(page,limit);
        List<OdersDto> odersDtoList=odersService.selectBySelective(orders);
        PageInfo<OdersDto> info=new PageInfo<>(odersDtoList);
        resultInfoList.setTotal(info.getTotal());
        resultInfoList.setSelectList(odersDtoList);
        return resultInfoList;
    }

    /**
     * 被取消订单显示显示
     * @return
     */
    @GetMapping("/cancel_show")
    public ResultInfoList cancel_show(@RequestParam("page") int page,@RequestParam("limit") int limit){
        Orders orders=new Orders();
        orders.setState(2);
        ResultInfoList resultInfoList=new ResultInfoList();
        PageHelper.startPage(page,limit);
        List<OdersDto> odersDtoList=odersService.selectBySelective(orders);
        PageInfo<OdersDto> info=new PageInfo<>(odersDtoList);
        resultInfoList.setTotal(info.getTotal());
        resultInfoList.setSelectList(odersDtoList);
        return resultInfoList;
    }

    /**
     * 去发货
     * @param json
     * @return
     */
    @GetMapping("/allocate")
    public Status_guana allocate(@RequestBody JSONObject json){
        String id=json.getString("id");
        Status_guana status_guana=new Status_guana();
        Orders orders=new Orders();
        orders.setReceive(0);
        orders.setId(id);
        try {
            int i=odersService.updateByPrimaryKeySelective(orders);
            if(i>0){
                status_guana.setMsg("发货成功");
                status_guana.setStatus(true);
            }else {
                status_guana.setMsg("发货失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            status_guana.setMsg("发货失败");
            return status_guana;
        }
        return status_guana;

    }




}
