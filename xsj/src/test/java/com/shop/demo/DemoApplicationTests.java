package com.shop.demo;

import com.shop.demo.dto.GoodsTypeProduct;
import com.shop.demo.pojo.Buycar;
import com.shop.demo.pojo.Orders;
import com.shop.demo.pojo.Product;
import com.shop.demo.pojo.Productimgs;
import com.shop.demo.service.OdersService;
import com.shop.demo.service.ProductService;
import com.shop.demo.service.ProductimgsService;
import com.shop.demo.service.WXDataService;
import com.shop.demo.utiles.FileServerAddr;
import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    ProductimgsService productimgsService;
    @Test
    public void  Prodctimgs(){
        String productID="1";
        int sort=1;
        Productimgs productimgs=new Productimgs();
        productimgs.setImgid(UUID.randomUUID().toString());
        productimgs.setImgurl("12346");
        productimgs.setProductid(productID);
        productimgs.setSort(sort);
        productimgsService.insert(productimgs);
    }
    @Test
    public void pathTest(){
        String imgPath="group1/M00/00/00/rBDSal74b1mAKhu7AA5rjT8cJoM552.png";
        String[] path=imgPath.split("/");
        String groupName=path[0];
        String fileName="M00/00/00/"+path[path.length-1];
        System.out.println(groupName+" "+fileName);
    }
    @Test
    public void select(){
        List<Productimgs> productimgsList=productimgsService.selectByproductid("1");
        System.out.println(productimgsList);
    }
    @Test
    public void fileServer(){
        System.out.println(FileServerAddr.getFileServer());
    }

    @Autowired
    ProductService productService;
    @Test
    public void Product(){
        Product product=new Product();
        product.setPthoto("131321");
        product.setId("2");
        System.out.println(product.getPthoto());
        productService.insertSelective(product);
    }
    @Test
    public void selectPrpduct(){
        Product product=new Product();
        product.setTypename("男士");
        product.setRecommend(0);
        System.out.println(productService.selectBySelective(product));
    }
    @Autowired
    OdersService odersService;
    @Test
    public void selectOders(){
        Orders orders=new Orders();
        orders.setIspay(1);
        System.out.println(odersService.selectBySelective(orders));
    }





    @Autowired
    WXDataService wxDataService;
    @Test
    public void selectalluser(){
        System.out.println(wxDataService.selectAllCustomer());
    }


    @Test
    public void selectIndexProduct(){
        String cmd="hot";
        if(cmd.equals("recommend")){
            System.out.println(wxDataService.selectrecommendList());
        }else if(cmd.equals("oldest")){
            System.out.println(wxDataService.selectoldestList());
        }else if(cmd.equals("hot")){
            System.out.println(wxDataService.selecthotList());
        }
    }

    @Test
    public void GoodsTypeProduct(){
        System.out.println(wxDataService.selectGoodsTypeProduct());
    }

    @Test
    public void selectProductDetails(){
        String id="1";
        System.out.println(wxDataService.selectProductDetails(id));
    }


    @Test
    public void addBuyCar(){
        Buycar buycar = new Buycar();
        String productid="11";
        String openid="11";
        buycar.setProductid(productid);
        buycar.setOpenid(openid);
        wxDataService.insert(buycar);
    }

    @Test
    public void selectByopenid(){
        String openid="11";
        wxDataService.selectByopenid(openid);
    }

    @Test
    public void deleteBuyCar(){
        Map<String, Object> map = new HashMap<String, Object>();
        String id="5";
        String openid="obUGC4rrDf-HgmFt5TrvIzSkDHQo";
        map.put("id",id);
        map.put("openid",openid);
        wxDataService.deleteone(map);
    }


}
