package com.example.frontend.connection;

import com.example.frontend.Model.User;
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

    public Request getRequestCreateUser(String url, User user, String token, String json) {

       /* String json = "{\n" +
                "\"firstName\":\"Phuc\",\n" +
                "\"lastName\":\"Huynh\", \n" +
                "\"email\":\"testw@test.com\", \n" +
                "\"enabled\":\"true\", \n" +
                "\"username\":\"postmaneeeee\",\n" +
                "\"credentials\":[{\"type\":\"password\",\"value\":\"Master123\",\"temporary\":false}]\n" +
                "}";*/
        RequestBody body = RequestBody.create(JSON,json);

/*        RequestBody body = new FormBody.Builder()
                .add("firstName","Hallo")
                .add("lastName","Hello")
                .add("email","test@d.com")
                .add("enabled","true")
                .add("username","dumami")
                .build();
        System.out.println(body.contentType().subtype());*/
            /*




        //String credentialsvalue = "[{\"type\":\"password\",\"value\":" + user.getPassword() + ",\"temporary\":false}]";
        RequestBody requestBody = new FormBody.Builder()
                .add("firstName",user.getFirstName())
                .add("lastName",user.getLastName())
                .add("email", user.getEmail())
                .add("enabled","true")
                .add("username",user.getUserName())
                //.add("credentials",credentialsvalue)
                .build();

        //RequestBody body = RequestBody.create(requestBody.contentType().toString(),JSON);*/

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
}
