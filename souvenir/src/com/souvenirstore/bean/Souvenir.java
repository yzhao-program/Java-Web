package com.souvenirstore.bean;

import java.math.BigDecimal;

public class Souvenir {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer sales;
    private String imgPath = "static/img/default.jpg";

    public Souvenir() {
    }

    public Souvenir(Integer id, String name, BigDecimal price, Integer sales, String imgPath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
        if (imgPath != null && !"".equals(imgPath)) {
            this.imgPath = imgPath;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        if (imgPath != null && !"".equals(imgPath)) {
            this.imgPath = imgPath;
        }
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
