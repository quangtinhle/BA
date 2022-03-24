package com.example.frontend.connection;

import okhttp3.*;

import java.io.IOException;

public class OkhttpConnection {

    private static OkhttpConnection instance = null;
    private OkHttpClient client = null;

    private OkhttpConnection() {
        client = new OkHttpClient.Builder()
                .build();
    }


    public static OkhttpConnection getInstance() {
        if(instance == null) {
            return new OkhttpConnection();
        }
        else
            return instance;
    }

    public Request getRequest(String url) {


        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type","client_credentials")
                .add("client_id","admin-cli")
                .add("client_secret","pgozzbdluq6dcSW9jn31VVhxFu3UYWiU")
                .build();



        Request request = new Request.Builder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .url(url)
                .post(requestBody)
                .build();
        return request;
    }

    public Response getResponse(Request request) {
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
