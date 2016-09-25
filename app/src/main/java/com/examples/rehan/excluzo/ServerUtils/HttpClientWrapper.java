package com.examples.rehan.excluzo.ServerUtils;

import android.util.Log;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by rehan r on 06-09-2016.
 */
public class HttpClientWrapper {

    OkHttpClient client;

    public HttpClientWrapper(){
        client = new OkHttpClient();
    }

    public String doPostRequest(String url, String json) throws IOException {

        //Log.e(url,"-----");
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        Log.e("<------>"+url+"<----->",responseString);
        return responseString;
    }


}
