package com.example.frontend.service;

import com.example.frontend.Entity.User;
import com.example.frontend.connection.OkhttpConnection;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    private OkhttpConnection connection = OkhttpConnection.getInstance();
    private String tokenEndPointUrl = "http://localhost:8280/auth/realms/master/protocol/openid-connect/token";

    public  String createUser()
    {
        return "Work...";
    }

    public String getAccessToken() {

        Response response = connection.getResponse(connection.getRequest(tokenEndPointUrl));
        String body = "";
        try {
            body = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject bodyJson = new JsonParser().parse(body).getAsJsonObject();
        return body;
    }

}
