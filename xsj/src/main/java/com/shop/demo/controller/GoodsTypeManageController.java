package com.shop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.demo.pojo.Goodstype;
import com.shop.demo.service.GoodsTypeService;
import com.shop.demo.utiles.Status_guana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品管理后台接口
 * @author GUANA
 */
@Controller
@ResponseBody
@RequestMapping("/goodsType")
public class GoodsTypeManageController {
    @Autowired
    GoodsTypeService goodsTypeService;

    /**
     * 显示所有商品分类信息
     * @return
     */
    @PostMapping("/show_all")
    public List<Goodstype> showAll(){
        return goodsTypeService.showAll();
    }

    /**
     * 添加商品类型
     * @param json
     * @return
     */
    @PostMapping("/add")
    public Status_guana add(@RequestBody JSONObject json){
        String name=json.getString("name");
        String state=json.getString("state");
        Goodstype goodstype=new Goodstype();
        Status_guana status_guana=new Status_guana();
        if(!state.equals("1") && !state.equals("0")){
            status_guana.setMsg("状态输入错误,添加失败");
            return  status_guana;
        }
        goodstype.setName(name);
        if(state!=null){
            goodstype.setState(Integer.parseInt(state));
        }

        try {
            int i=goodsTypeService.insertSelective(goodstype);
            if(i>0){
                status_guana.setMsg("添加成功");
                status_guana.setStatus(true);
            }else {
                status_guana.setMsg("添加失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            status_guana.setMsg("添加失败");
            return  status_guana;
        }
        return status_guana;
    }

    /**
     * 删除商品类型
     * @param json
     * @return
     */
    @PostMapping("/delete")
    public Status_guana delete(@RequestBody JSONObject json){
        Status_guana status_guana=new Status_guana();
        String id=json.getString("id");
        try {
            int i=goodsTypeService.deleteByPrimaryKey(id);
            if(i>0){
                status_guana.setMsg("删除成功");
                status_guana.setStatus(true);
            }else {
                status_guana.setMsg("删除失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            status_guana.setMsg("删除失败");
            return  status_guana;
        }
        return status_guana;
    }

    /**
     * 修改商品类型
     * @param json
     * @return
     */
    @PostMapping("/update")
    public Status_guana dupdate(@RequestBody JSONObject json){
        Status_guana status_guana=new Status_guana();
        String id=json.getString("id");
        String name=json.getString("name");
        String state=json.getString("state");
        Goodstype goodstype=new Goodstype();
        if(state!=null){
            goodstype.setState(Integer.parseInt(state));
        }

        goodstype.setName(name);
        goodstype.setId(id);
        try {
            int i=goodsTypeService.updateByPrimaryKeySelective(goodstype);
            if(i>0){
                status_guana.setMsg("修改成功");
                status_guana.setStatus(true);
            }else {
                status_guana.setMsg("修改失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            status_guana.setMsg("修改失败");
            return  status_guana;
        }
        return status_guana;
    }
}
