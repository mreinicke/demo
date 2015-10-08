package com.tech.assignment.controller;

import com.tech.assignment.model.LoginRequest;
import com.tech.assignment.model.LoginResponse;
import com.tech.assignment.util.AbstractIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginControllerIT_Test extends AbstractIntegrationTest {
    private LoginRequest loginRequest;

    private static final String USER = "user";
    private static final String BAD_LOGIN = "Invalid user/password combination";

    @Before
    public void setup(){
        loginRequest = new LoginRequest();
        loginRequest.setEmail(USER);
        loginRequest.setPassword("password");
    }

    @Test
    public void test_login(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LoginResponse retVal =(LoginResponse)this.postEntityWithHeaders("/login/authenticate", LoginResponse.class, loginRequest, headers);

        assertNotNull(retVal);
        assertEquals(USER, retVal.getUserName());
    }

    @Test
    public void test_badLogin(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        loginRequest.setEmail("bad");

        LoginResponse retVal =(LoginResponse)this.postEntityWithHeaders("/login/authenticate", LoginResponse.class, loginRequest, headers);

        assertNotNull(retVal);
        assertEquals(BAD_LOGIN, retVal.getStatus());
    }
}
