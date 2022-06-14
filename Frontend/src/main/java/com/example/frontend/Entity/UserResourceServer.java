package com.example.frontend.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
@Getter
@Setter
public class UserResourceServer {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;

    public UserResourceServer() {
    }

    public UserResourceServer(String id) {
        this.id = id;
    }

    public UserResourceServer(String id, String firstname, String lastname, String username, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
    }

}
