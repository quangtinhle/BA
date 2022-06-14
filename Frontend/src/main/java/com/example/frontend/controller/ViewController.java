package com.example.frontend.controller;

import com.example.frontend.Entity.AccessToken;
import com.example.frontend.Entity.Greeting;
import com.example.frontend.Entity.UserAS;
import com.example.frontend.Entity.UserResourceServer;
import com.example.frontend.Model.Credentials;
import com.example.frontend.Model.User;
import com.example.frontend.Model.UserDTO;
import com.example.frontend.convert.ReciverUserConvert;
import com.example.frontend.service.UserResourceServerService;
import com.example.frontend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Base64;
import java.util.NoSuchElementException;


@Controller
public class ViewController {

    @Autowired
    OAuth2AuthorizedClientService auth2AuthorizedClientService;

@Autowired UserService userService;
@Autowired UserResourceServerService userResourceServerService;

    @GetMapping("/registration")
    public String getRegisterForm(Model model) {
        model.addAttribute("messsage", "REGISTER FORM");
        model.addAttribute("user", new UserDTO());
        return "registrationForm";
    }


    @GetMapping("/userinformation")
    public String getDefaultView(Model model, @AuthenticationPrincipal OidcUser principal) {
        String userId = principal.getName();


        UserResourceServer userResourceServer = null;
        try {
             userResourceServer = userResourceServerService.getUserResourceServer(userId);
        }catch (NoSuchElementException e)
        {
            e.printStackTrace();
        }


        if(userResourceServer == null) {
            UserResourceServer newUser = new UserResourceServer(userId);
            ObjectMapper om = new ObjectMapper();
            OidcIdToken idToken = principal.getIdToken();
            String idTokenValue = idToken.getTokenValue();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

            OAuth2AuthorizedClient oAuth2AuthorizedClient = auth2AuthorizedClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId()
                    ,oauthToken.getName());

            String jwtAccessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
            String[] chunks = jwtAccessToken.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();

            String payload = new String(decoder.decode(chunks[1]));

            AccessToken accessToken = null;

            try {
                accessToken = om.readValue(payload,AccessToken.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            newUser.setUsername(accessToken.getPreferred_username());
            newUser.setFirstname(accessToken.getGiven_name());
            newUser.setLastname(accessToken.getFamily_name());
            newUser.setEmail(accessToken.getEmail());
            userResourceServerService.createUser(newUser);

            accessToken.getRealm_access().getRoles().stream().forEach(System.out::println);

        }


   /*     if(userResourceServer == null) {
            UserResourceServer newUser = new UserResourceServer(userId);
            String jwt = userResourceServerService.getUserInformationfromID(userId);

            UserAS userInfo = null;
            try {
                 userInfo = om.readValue(jwt,UserAS.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            newUser.setFirstname(userInfo.firstName);
            newUser.setLastname(userInfo.lastName);
            newUser.setUsername(userInfo.username);
            newUser.setEmail(userInfo.email);
            userResourceServerService.createUser(newUser);
        }*/





        return "userinformation";
    }


    @PostMapping("/signin")
    public String getLoginForm(@ModelAttribute UserDTO userDTO, Model model) {
        model.addAttribute("messsage", "SIGN IN");
        userService.createUser(userDTO);
        //model.addAttribute("user", new User());
        System.out.println("Hallo chung may");
        return "signinForm";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }



}
