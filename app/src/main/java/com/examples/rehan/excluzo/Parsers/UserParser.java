package com.examples.rehan.excluzo.Parsers;

import com.examples.rehan.excluzo.Models.User;

import org.json.JSONObject;

/**
 * Created by rehan r on 02-09-2016.
 */
public class UserParser {

    public User parse(JSONObject object){
        User user = null;
        String userid = "",username = "",email = "",mobile = "",address = "",password = "";
        int age = -1,gender = -1;

        try {
            if (object.has("userid")) {
                userid = object.getString("userid");
            }
            if (object.has("username")){
                username = object.getString("username");
            }
            if (object.has("email")){
                email = object.getString("email");
            }
            if (object.has("mobile")){
                mobile = object.getString("mobile");
            }
            if (object.has("gender")){
                gender = object.getInt("gender");
            }
            if (object.has("age")){
                age = object.getInt("age");
            }
            if (object.has("address")){
                address = object.getString("address");
            }
            if(object.has("password")){
                password = object.getString("password");
            }

            user = new User(userid,username,email,password,mobile,age,gender,address);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

}
