package com.shop.demo;

import com.shop.demo.pojo.Productimgs;
import com.shop.demo.service.ProductimgsService;
import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.util.List;
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

}
