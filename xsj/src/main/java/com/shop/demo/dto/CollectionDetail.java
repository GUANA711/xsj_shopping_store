package com.shop.demo.dto;

public class CollectionDetail {
    private String collecttime;
    private String productid;
    private String name;
    private Double price;
    private Integer stock;
    private Integer number;
    private String pthoto;

    public String getCollecttime() {
        return collecttime;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setCollecttime(String collecttime) {
        this.collecttime = collecttime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPthoto() {
        return pthoto;
    }

    public void setPthoto(String pthoto) {
        this.pthoto = pthoto;
    }
}
