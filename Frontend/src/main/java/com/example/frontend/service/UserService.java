package com.example.frontend.service;

import com.example.frontend.Model.Credentials;
import com.example.frontend.Model.User;
import com.example.frontend.Model.UserDTO;
import com.example.frontend.connection.OkhttpConnection;
import com.example.frontend.convert.ReciverUserConvert;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.Arrays;


@Service
public class UserService {

    @Value("${endPointUser}")
    private String createUserEndPoint;
    @Value("${tokenEndPointUrl}")
    private String tokenEndPoint;
    @Value("${keycloak}")
    private String keycloak;
    @Value("${requiredAction}")
    private String requireAction;
    @Value("${role}")
    private String ROLE;


    private OkhttpConnection connection = OkhttpConnection.getInstance();


    private Gson gson = new Gson();

    //create new user on authorization server of keycloak
    public  String createUser(UserDTO userDTO)
    {
        Credentials credentials = new Credentials(userDTO.getPassword());
        User user = ReciverUserConvert.converttoUser(userDTO, Arrays.asList(credentials));
        if(userDTO.isTwoFa()) {
            user.setRequiredActions(Arrays.asList(requireAction));
        }
        String token = getAccessToken();
        String url = keycloak + createUserEndPoint;
        Request request = connection.getRequestCreateUser(url,user,token,gson.toJson(user));
        Response response = connection.getResponse(request);



        String createdId = getCreatedUserId(response);
        System.out.println(createdId);
        String roleId = getRolleId();
        System.out.println(roleId);

        String res = "";
        try {
            res = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(res);

        return response.message().toString();
    }


//get access token for keycloak admin cli.
    public String getAccessToken() {

        String url = keycloak + tokenEndPoint;
        Response response = connection.getResponse(connection.getRequestToken(url ));
        String body = "";
        try {
            body = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject bodyJson = new JsonParser().parse(body).getAsJsonObject();

        return bodyJson.get("access_token").getAsString();
    }

    public String getCreatedUserId(Response response) {
        String location = response.header("Location").toString();
        String createdUserId = location.substring(location.length() - 36);
        return createdUserId;
    }

    public String getRolleId() {

        String url = "http://localhost:8280/auth/admin/realms/appsdeveloperblog/roles/";
        Request request = connection.getRequestRoleId(url + ROLE,getAccessToken());
        Response response = connection.getResponse(request);
        JsonObject jsonObject = null;
        try {
            jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject);
        return jsonObject.get("id").getAsString();

    }

}
