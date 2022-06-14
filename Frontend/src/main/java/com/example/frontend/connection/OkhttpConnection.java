package com.example.frontend.connection;

import com.example.frontend.Model.User;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;

public class OkhttpConnection {

    private static OkhttpConnection instance = null;
    private OkHttpClient client = null;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

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

    public Request getRequestToken(String url) {


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

    public Request getRequestCreateUser(String url, String token, String json) {


        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","Bearer " + token)
                .url(url)
                .post(body)
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

    public Request getRequestRoleId(String url, String token) {
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","Bearer " + token)
                .url(url)
                .build();
        return request;
    }


    public Request getRequestsetUserRole(String url, String token, String json) {

        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","Bearer " + token)
                .url(url)
                .post(body)
                .build();


        return request;
    }


    public Request getRequestgetUserwithId(String url, String token) {
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","Bearer " + token)
                .url(url)
                .build();
        return request;
    }

}
