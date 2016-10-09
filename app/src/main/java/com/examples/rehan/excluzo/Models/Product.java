package com.examples.rehan.excluzo.Models;

import java.io.Serializable;

/**
 * Created by rehan r on 24-09-2016.
 */
public class Product implements Serializable{
    String productid,subid,manuid,productname,description,image_url;
    int price,stockCount,discount,numberOfRatings;
    double ratings;


    public Product(String productid, String subid, String manuid, String productname, String description, String image_url, int price, int stockCount, int discount, int numberOfRatings, double ratings) {
        this.productid = productid;
        this.subid = subid;
        this.manuid = manuid;
        this.productname = productname;
        this.description = description;
        this.image_url = image_url;
        this.price = price;
        this.stockCount = stockCount;
        this.discount = discount;
        this.numberOfRatings = numberOfRatings;
        this.ratings = ratings;
    }

    public Product(){

    }
    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public String getManuid() {
        return manuid;
    }

    public void setManuid(String manuid) {
        this.manuid = manuid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
}
