package com.example.frontend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken {

    private int exp;
    private int iat;
    private int auth_time;
    private String jti;
    private String iss;
    private String aud;
    private String sub;
    private String typ;
    private String azp;
    private String nonce;
    private String session_state;
    private String acr;
    private RealmAccess realm_access;
    private String scope;
    private String sid;
    private boolean email_verified;
    private String name;
    private String preferred_username;
    private String given_name;
    private String family_name;
    private String email;

}
