package com.example.frontend.Model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String enabled;
    private List<Credentials> credentials;
    private List<String> requiredActions;


}
