package com.examples.rehan.excluzo.ServerUtils;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by rehan r on 07-09-2016.
 */
public class ServerRequest {

    HttpClientWrapper clientWrapper;
    public ServerRequest(){
        clientWrapper = new HttpClientWrapper();
    }

    public JSONObject userLogin(Context context, String email, String password){
        JSONObject jsonObject = null;
        try {
            JSONObject request = new JSONObject();
            request.put("email", email);
            request.put("password", password);
            Log.e("JSON-REQUEST",request.toString());
            String response = clientWrapper.doPostRequest(Urls.BASE_URL+Urls.USER_LOGIN,request.toString());
            jsonObject = new JSONObject(response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
}
