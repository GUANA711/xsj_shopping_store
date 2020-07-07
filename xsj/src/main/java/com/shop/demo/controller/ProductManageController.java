package com.shop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.demo.pojo.Goodstype;
import com.shop.demo.pojo.Product;
import com.shop.demo.pojo.Productimgs;
import com.shop.demo.service.GoodsTypeService;
import com.shop.demo.service.ProductService;
import com.shop.demo.service.ProductimgsService;
import com.shop.demo.utiles.FastDFSClient;
import com.shop.demo.utiles.FileServerAddr;
import com.shop.demo.utiles.ResultInfoList;
import com.shop.demo.utiles.Status_guana;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商品管理接口
 * @author GUANA
 */
@Controller
@ResponseBody
@RequestMapping("/product")
public class ProductManageController {
    @Autowired
    ProductService productService;
    @Autowired
    GoodsTypeService goodsTypeService;
    @Autowired
    ProductimgsService productimgsService;

    /**
     *显示所有商品
     * @return
     */

    @GetMapping("/show")
    public ResultInfoList show(@RequestParam Integer page, @RequestParam Integer limit) {
        ResultInfoList resultInfoList=new ResultInfoList();
        PageHelper.startPage(page,limit);
        List<Product> productList=productService.show();
        PageInfo<Product> info=new PageInfo<>(productList);
        resultInfoList.setTotal(info.getTotal());
        resultInfoList.setCode(0);
        resultInfoList.setSelectList(productList);
        return resultInfoList;
    }

    /**
     * 添加商品
     * @param name
     * @param price
     * @param title
     * @param stock
     *
     * @param description
     * @param recommend
     * @param oldest
     * @param hot
     * @param typeid
     * @param file
     * @return
     */
    @PostMapping("/add")
    public Status_guana add(@RequestParam("name") String name ,
                            @RequestParam("price") String price,
                            @RequestParam("title")  String title,
                            @RequestParam("stock") String stock,
                            @RequestParam("description")  String description,
                            @RequestParam("recommend")  String recommend,
                            @RequestParam("oldest") String oldest,
                            @RequestParam("hot")  String hot,
                            @RequestParam("typeid")  String typeid,
                            @RequestParam("fields")  String fields,
                            @RequestParam("file") MultipartFile file) {
        Status_guana status_guana = new Status_guana();
        Product product = new Product();
        String id = UUID.randomUUID().toString();
        Date date_Date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(date_Date).toString();

        if (stock != null) {
            product.setStock(Integer.parseInt(stock));
        }

        if (recommend != null) {
            product.setRecommend(Integer.parseInt(recommend));
        }
        product.setDescription(description);
        if (oldest != null) {
            product.setOldest(Integer.parseInt(oldest));
        }
        if (hot != null) {
            product.setHot(Integer.parseInt(hot));
        }
        if (price != null) {
            product.setPrice(Double.parseDouble(price));
        }

        Goodstype goodstype = goodsTypeService.selectByPrimaryKey(typeid);
        String typename = goodstype.getName();

        product.setId(id);
        product.setName(name);
        product.setTitle(title);
        product.setDescription(description);
        product.setPublishtime(date);
        product.setTypename(typename);
        product.setTypeid(typeid);
        product.setFields(fields);


        //添加封面图片
        if (file != null && !file.isEmpty()) {
            try {
                FastDFSClient dfsClient = new FastDFSClient();
                //获取文件后缀名
                String fileName = file.getOriginalFilename();
                String fileExName = fileName.substring(fileName.lastIndexOf(".") + 1);
                //返回文件存储在dfs的URL
                String url = dfsClient.uploadFile(file.getBytes(), fileExName);
                if (url != null && !url.equals("")) {
                    System.out.println(url);
                    product.setPthoto(url);
                    int i=productService.insertSelective(product);
                    if(i>0){
                        status_guana.setMsg("添加成功");
                        status_guana.setStatus(true);
                    }else {
                        status_guana.setMsg("添加失败");
                    }

                } else {
                    status_guana.setMsg("图片添加失败");
                    return status_guana;
                }

            } catch (Exception e) {
                e.printStackTrace();
                status_guana.setMsg("图片添加失败");
                return status_guana;
            }
        }

        return status_guana;
    }

    /**
     * ckeditor上传文件
     * @param
     * @param response
     * @param request
     * @return
     */
    @PostMapping("/ckImg")
    public Status_guana ckImg(@RequestParam("upload")MultipartFile upload, HttpServletResponse response, HttpServletRequest request){
        Status_guana status_guana=new Status_guana();
        String fileServer=FileServerAddr.getFileServer();
        Productimgs productimgs=new Productimgs();
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                FastDFSClient dfsClient = new FastDFSClient();
                //获取文件后缀名
                String fileName = upload.getOriginalFilename();
                String fileExName = fileName.substring(fileName.lastIndexOf(".") + 1);
                //返回文件存储在dfs的URL
                String url = dfsClient.uploadFile(upload.getBytes(), fileExName);
                String imgpath = fileServer+"/"+url;
                response.setContentType("text/html;charset=UTF-8");
                String callback = request.getParameter("CKEditorFuncNum");
                PrintWriter out = response.getWriter();
                status_guana.setData(imgpath);
                status_guana.setStatus(true);
                status_guana.setMsg("图片添加成功");

            } catch (Exception e) {
                e.printStackTrace();
                status_guana.setMsg("图片添加失败");
                return status_guana;
            }
        }else {

            status_guana.setMsg("图片添加失败");
        }

        return status_guana;
    }

    @PostMapping("/update")
    public Status_guana update(@RequestParam("id") String id,
                               @RequestParam("name") String name ,
                               @RequestParam("price") String price,
                               @RequestParam("title")  String title,
                               @RequestParam("stock") String stock,
                               @RequestParam("description")  String description,
                               @RequestParam("recommend")  String recommend,
                               @RequestParam("oldest") String oldest,
                               @RequestParam("hot")  String hot,
                               @RequestParam("fields")  String fields,
                               @RequestParam("typename")  String typename,
                               @RequestParam("file") MultipartFile file){
        Status_guana status_guana=new Status_guana();
        Product product=new Product();
        product.setName(name);
        if (stock != null) {
            product.setStock(Integer.parseInt(stock));
        }

        if (recommend != null) {
            product.setRecommend(Integer.parseInt(recommend));
        }
        product.setDescription(description);
        if (oldest != null) {
            product.setOldest(Integer.parseInt(oldest));
        }
        if (hot != null) {
            product.setHot(Integer.parseInt(hot));
        }
        if (price != null) {
            product.setPrice(Double.parseDouble(price));
        }
        product.setTitle(title);
        product.setDescription(description);
        product.setTypename(typename);
        product.setId(id);
        product.setFields(fields);
        product.setTypeid(goodsTypeService.selectByname(typename).getId());


        if (file != null && !file.isEmpty()) {
            try {
                FastDFSClient dfsClient = new FastDFSClient();
                //获取文件后缀名
                String fileName = file.getOriginalFilename();
                String fileExName = fileName.substring(fileName.lastIndexOf(".") + 1);
                //返回文件存储在dfs的URL
                String url = dfsClient.uploadFile(file.getBytes(), fileExName);
                dfsClient.delFile(productService.selectByPrimaryKey(id).getPthoto());
                if (url != null && !url.equals("")) {
                    product.setPthoto(url);
                    int i=productService.updateByPrimaryKeySelective(product);
                    if(i>0){
                        status_guana.setMsg("图片修改成功");
                        status_guana.setStatus(true);
                    }else {
                        status_guana.setMsg("图片修改失败");
                    }

                } else {
                    status_guana.setMsg("图片修改失败");
                    return status_guana;
                }

            } catch (Exception e) {
                e.printStackTrace();
                status_guana.setMsg("图片修改失败");
                return status_guana;
            }
        }


        return status_guana;
    }

    /**
     * 搜索商品
     * @param json
     * @return
     */
    @PostMapping("/select/{page}/{limit}")

    public  ResultInfoList select(@RequestBody JSONObject json,@PathVariable("page") int page,@PathVariable("limit") int limit) {
        String id = json.getString("id");
        String name = json.getString("name");
        String price = json.getString("price");
        String title = json.getString("title");
        String stock = json.getString("stock");
        String number = json.getString("number");
        String description = json.getString("description");
        String publishtime = json.getString("publishtime");
        String recommend = json.getString("recommend");
        String oldest = json.getString("oldest");
        String hot = json.getString("hot");
        String typeid = json.getString("typeid");
        String typename = json.getString("typename");
        String fields = json.getString("fields");

        Product product = new Product();
        product.setName(name);
            if (price != null) {
                product.setPrice(Double.parseDouble(price));
            }

            if (stock != null) {
                product.setStock(Integer.parseInt(stock));
            }
            if (number != null) {
                product.setNumber(Integer.parseInt(number));
            }
            if (recommend != null) {
                product.setRecommend(Integer.parseInt(recommend));
            }
            product.setDescription(description);
            if (oldest != null) {
                product.setOldest(Integer.parseInt(oldest));
            }
            if (hot != null) {
                product.setHot(Integer.parseInt(hot));
            }
            product.setTitle(title);
            product.setTypename(typename);
            product.setId(id);
            product.setTypeid(typeid);
            product.setPublishtime(publishtime);
            product.setFields(fields);

        ResultInfoList resultInfoList=new ResultInfoList();
        PageHelper.startPage(page,limit);
        List<Product> productList=productService.selectBySelective(product);
        PageInfo<Product> info=new PageInfo<>(productList);
        resultInfoList.setTotal(info.getTotal());
        resultInfoList.setSelectList(productList);
        return resultInfoList;


        }

    /**
     * 删除商品
     * @param json
     * @return
     */
     @PostMapping("/delete")
     public Status_guana delete(@RequestBody JSONObject json){
        Status_guana status_guana=new Status_guana();
        String id=json.getString("id");
        try {
            int i=productService.deleteByPrimaryKey(id);
            if(i>0){
                status_guana.setMsg("删除成功");
                status_guana.setStatus(true);
            }else {
                status_guana.setMsg("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            status_guana.setMsg("删除失败");
            return status_guana;
        }

        return status_guana;

     }


}
