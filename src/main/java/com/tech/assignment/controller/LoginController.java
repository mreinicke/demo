package com.tech.assignment.controller;

import com.tech.assignment.model.LoginRequest;
import com.tech.assignment.model.LoginResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class LoginController {

    @RequestMapping(value = "/login/authenticate", method = RequestMethod.POST)
    public @ResponseBody LoginResponse login(@RequestBody LoginRequest loginRequest,  HttpServletRequest request ) {
        if(loginRequest.getEmail().equals("user") && loginRequest.getPassword().equals("password")) {
            LoginResponse response = new LoginResponse();
            response.setSessionId(UUID.randomUUID().toString());
            response.setUserName(loginRequest.getEmail());
            response.setStatus("OK");

            return response;
        }

        LoginResponse response = new LoginResponse();
        response.setSessionId(null);
        response.setStatus("Invalid user/password combination");
        return response;
    }
}
