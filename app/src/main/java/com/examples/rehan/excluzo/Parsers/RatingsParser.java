package com.examples.rehan.excluzo.Parsers;

import com.examples.rehan.excluzo.Models.Rating;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rehan r on 27-09-2016.
 */
public class RatingsParser {

    public Rating parse(JSONObject jsonObject){
        String userid = "", productid = "", description ="";
        double ratings = -1;
        Rating rating = null;
        try {
            if (jsonObject.has("userid"))
                userid = jsonObject.getString("userid");
            if (jsonObject.has("productid"))
                productid = jsonObject.getString("productid");
            if (jsonObject.has("description"))
                description = jsonObject.getString("description");
            if (jsonObject.has("ratings"))
                ratings = jsonObject.getDouble("description");
            rating = new Rating(userid,productid,description,ratings);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rating;
    }


    public List<Rating> parse(JSONArray jsonArray){
        List<Rating> ratingList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                ratingList.add(parse(jsonArray.getJSONObject(i)));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ratingList;
    }
}
