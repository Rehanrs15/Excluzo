package com.examples.rehan.excluzo.Models;

/**
 * Created by rehan r on 27-09-2016.
 */
public class Rating {

    String userid,productid,review;
    double rating;

    public Rating(String userid, String productid, String review, double rating) {
        this.userid = userid;
        this.productid = productid;
        this.review = review;
        this.rating = rating;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
