package com.example.frontend.convert;

import com.example.frontend.Model.Credentials;
import com.example.frontend.Model.User;
import com.example.frontend.Model.UserDTO;

import java.util.List;

public class ReciverUserConvert {

    private ReciverUserConvert () {}

    public static User converttoUser(UserDTO userDTO, List<Credentials> list, List<String> requiresActions) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .enabled("true")
                .username(userDTO.getUserName())
                .credentials(list)
                .requiredActions(requiresActions)
                .build();


    }
}
