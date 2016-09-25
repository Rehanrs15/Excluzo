package com.examples.rehan.excluzo.Parsers;

import com.examples.rehan.excluzo.Models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rehan r on 24-09-2016.
 */
public class ProductParser {

    public List<Product> parse(JSONArray jsonArray){
        List<Product> products = new ArrayList<>();
        for (int i = 0;i < jsonArray.length(); i++){
            try {
                products.add(parse(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return products;
    }


    public Product parse(JSONObject object){
        Product product = null;
        String productid="",subid="",manuid="",productname="",description="",image_url="";
        int price=-1,count=-1,discount = -1,number_of_ratings = -1;
        double ratings = 0.0;

        try {
            if (object.has("productid")) {
                productid = object.getString("productid");
            }
            if (object.has("subid")) {
                subid = object.getString("subid");
            }
            if (object.has("manuid")) {
                manuid = object.getString("manuid");
            }
            if (object.has("productname")) {
                productname = object.getString("productname");
            }
            if (object.has("description")) {
                description = object.getString("description");
            }
            if (object.has("image_url")) {
                image_url = object.getString("image_url");
            }
            if (object.has("price")) {
                price = object.getInt("price");
            }
            if (object.has("count")) {
                count = object.getInt("count");
            }
            if (object.has("discout"))
                discount = object.getInt("discout");
            if (object.has("total_ratings"))
                number_of_ratings = object.getInt("total_ratings");
            if (object.has("ratings"))
                ratings = object.getDouble("ratings");

            product = new Product(productid,subid,manuid,productname,description,image_url,price,count,discount,
                    number_of_ratings,ratings);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return product;

    }
}
