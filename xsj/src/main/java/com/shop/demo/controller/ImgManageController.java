package com.shop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.shop.demo.pojo.Productimgs;
import com.shop.demo.service.ProductimgsService;
import com.shop.demo.utiles.FastDFSClient;
import com.shop.demo.utiles.Status_guana;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * 图片管理后台接口
 * @author GUANA
 */
@Controller
@ResponseBody
@RequestMapping("/fastDfs")
public class ImgManageController {
    @Autowired
    ProductimgsService productimgsService;

    /**
     * 商品图片添加
     * @param productID
     * @param imgSort
     * @param file
     * @return
     */
    @PostMapping("/add_img")
    public Status_guana add(@RequestParam("productID") String productID,@RequestParam("imgSort") String imgSort, @RequestParam("file")MultipartFile file)  {

        int sort=Integer.parseInt(imgSort);

        Productimgs productimgs=new Productimgs();
        Status_guana status_guana=new Status_guana();
        if(file!=null && !file.isEmpty()){
            try {
                FastDFSClient dfsClient=new FastDFSClient();
                //获取文件后缀名
                String fileName=file.getOriginalFilename();
                String fileExName=fileName.substring(fileName.lastIndexOf(".")+1);
                //返回文件存储在dfs的URL
                String url= dfsClient.uploadFile(file.getBytes(),fileExName);
                if(url!=null && !url.equals("")){
                    productimgs.setImgid(UUID.randomUUID().toString());
                    productimgs.setImgurl(url);
                    productimgs.setProductid(productID);
                    productimgs.setSort(sort);
                    int i=productimgsService.insert(productimgs);
                    if(i>0){
                        status_guana.setMsg("图片添加成功");
                        status_guana.setStatus(true);
                    }else {
                        status_guana.setMsg("图片添加失败");
                    }

                }else {
                    status_guana.setMsg("图片添加失败");
                }

            }catch (Exception e){
                e.printStackTrace();
                status_guana.setMsg("图片添加失败");
                return status_guana;
            }


        }
        return status_guana;
    }

    /**
     * 图片删除
     * @param json
     * @return
     */
    @PostMapping("/delete_img")
    public Status_guana delete(@RequestBody JSONObject json){

        String imgID=json.getString("imgID");
        Status_guana status_guana=new Status_guana();
        try {
            Productimgs productimgs=productimgsService.selectByPrimaryKey(imgID);
            String imgPath=productimgs.getImgurl();
            //System.out.println(imgPath);
            FastDFSClient dfsClient=new FastDFSClient();
            Boolean isSucss= dfsClient.delFile(imgPath);
            if(isSucss){
                int i=productimgsService.deleteByPrimaryKey(productimgs.getImgid());
                if(i>0){
                    status_guana.setStatus(true);
                    status_guana.setMsg("图片删除成功");
                }else {
                    status_guana.setMsg("图片删除失败");
                }

            }
            else {
                status_guana.setStatus(false);
                status_guana.setMsg("图片删除失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return status_guana;

        }

        return  status_guana;
    }

    /**
     * 图片修改
     * @param json
     * @return
     */
    @PostMapping("/update_img")
    public Status_guana update(@RequestBody JSONObject json){
        Status_guana status_guana=new Status_guana();
        Productimgs productimgs=new Productimgs();
        productimgs.setSort(Integer.parseInt(json.getString("sort")));
        productimgs.setProductid(json.getString("productid"));
        productimgs.setImgurl(json.getString("imgurl"));
        productimgs.setImgid(json.getString("imgid"));
        try {
            int i=productimgsService.updateByPrimaryKeySelective(productimgs);
            if(i>0){
                status_guana.setMsg("修改成功");
                status_guana.setStatus(true);
            }else {
                status_guana.setMsg("修改失败");
            }

        }catch (Exception e){

            return status_guana;
        }
        return status_guana;
    }

    @PostMapping("/select_img")
    public List<Productimgs> select(@RequestBody JSONObject json){
        String productid=json.getString("productid");
        try {
            List<Productimgs> productimgsList= productimgsService.selectByproductid(productid);
            return  productimgsList;
        }catch (Exception e){
            return null;
        }

    }

}
