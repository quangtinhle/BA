package com.example.frontend.service;

import com.example.frontend.Model.Credentials;
import com.example.frontend.Model.User;
import com.example.frontend.Model.UserDTO;
import com.example.frontend.connection.OkhttpConnection;
import com.example.frontend.convert.ReciverUserConvert;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private OkhttpConnection connection = OkhttpConnection.getInstance();
    private String tokenEndPointUrl = "http://localhost:8280/auth/realms/master/protocol/openid-connect/token";
    private String createUserEndPointUrl = "http://localhost:8280/auth/admin/realms/appsdeveloperblog/users";
    private Gson gson = new Gson();

    public  String createUser(UserDTO userDTO)
    {
        Credentials credentials = new Credentials(userDTO.getPassword());
        User user = ReciverUserConvert.converttoUser(userDTO, Arrays.asList(credentials));
        if(userDTO.isTwoFa()) {
            user.setRequiredActions(Arrays.asList("CONFIGURE_TOTP"));
        }
        String token = getAccessToken();
        Request request = connection.getRequestCreateUser(createUserEndPointUrl,user,token,gson.toJson(user));
        Response response = connection.getResponse(request);

        System.out.println(response.header("Location"));



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
