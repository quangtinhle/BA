package com.example.frontend.service;

import com.example.frontend.Entity.UserResourceServer;
import com.example.frontend.connection.OkhttpConnection;
import com.example.frontend.repository.UserResourceServerRepository;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserResourceServerService {

@Autowired UserService userService;
@Autowired UserResourceServerRepository userResourceServerRepository;

    private OkhttpConnection connection = OkhttpConnection.getInstance();


    public String getUserInformationfromID(String userID) {


            String url = "http://localhost:8280/auth/admin/realms/appsdeveloperblog/users/";
            Request request = connection.getRequestRoleId(url + userID,userService.getAccessToken());
            Response response = connection.getResponse(request);

        String jwt = null;
        try {
            jwt = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return jwt ;
    }

    public UserResourceServer getUserResourceServer(String id) {
        return userResourceServerRepository.findById(id).get();
    }

    public UserResourceServer createUser(UserResourceServer user) {
        return userResourceServerRepository.save(user);
    }


}
