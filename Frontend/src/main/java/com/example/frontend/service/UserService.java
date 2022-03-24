package com.example.frontend.service;

import com.example.frontend.Model.User;
import com.example.frontend.connection.OkhttpConnection;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    private OkhttpConnection connection = OkhttpConnection.getInstance();
    private String tokenEndPointUrl = "http://localhost:8280/auth/realms/master/protocol/openid-connect/token";
    private String createUserEndPointUrl = "http://localhost:8280/auth/admin/realms/appsdeveloperblog/users";

    public  String createUser(User user)
    {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        String token = getAccessToken();
        Request request = connection.getRequestCreateUser(createUserEndPointUrl,user,token,json);
        Response response = connection.getResponse(request);




        String res = "";
        try {
            res = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(res);

        return response.message().toString();
    }

    public String getAccessToken() {

        Response response = connection.getResponse(connection.getRequestToken(tokenEndPointUrl));
        String body = "";
        try {
            body = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject bodyJson = new JsonParser().parse(body).getAsJsonObject();

        return bodyJson.get("access_token").getAsString();
    }

}
