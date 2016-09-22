package com.examples.rehan.excluzo.Parsers;

import com.examples.rehan.excluzo.Models.Electronics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by rehan r on 22-09-2016.
 */
public class ElectronicsParser {

    public Electronics parse(JSONObject object)
    {
        Electronics electronics=null;
        String productid="",subid="",manuid="",productname="",description="",image_url="";
        int price=-1,count=-1;

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
            electronics = new Electronics(productid,subid,manuid,productname,description,price
                                            ,count,image_url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return electronics;
    }

    public List<Electronics> parse(JSONArray jsonArray) {
        List<Electronics> electronicsList = null;
        for(int i = 0; i< jsonArray.length(); i++){
            try {
                electronicsList.add(parse(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return electronicsList;
    }
}
