package com.example.frontend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.keycloak.models.UserModel;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAS {
        public String id;
        public long createdTimestamp;
        public String username;
        public boolean enabled;
        public boolean totp;
        public boolean emailVerified;
        public String firstName;
        public String lastName;
        public String email;
        public ArrayList<UserModel.RequiredAction> requiredActions;
}
