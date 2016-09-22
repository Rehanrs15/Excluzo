package com.examples.rehan.excluzo.Models;

/**
 * Created by rehan r on 22-09-2016.
 */
public class Electronics {

    String productid,subid,manuid,productname,description,image_url;
    int price,count;

    public Electronics(String productid,String subid,String manuid,String productname,String description,int price,int count,String image_url) {

        this.productid=productid;
        this.subid=subid;
        this.productname=productname;
        this.description=description;
        this.price=price;
        this.count=count;
        this.image_url=image_url;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
