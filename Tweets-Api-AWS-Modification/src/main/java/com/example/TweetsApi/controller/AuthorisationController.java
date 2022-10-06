package com.example.TweetsApi.controller;

import com.example.TweetsApi.dto.UserLoginRequest;
import com.example.TweetsApi.dto.UserRegistration;
import com.example.TweetsApi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/tweetsapi/authorisation")
@AllArgsConstructor
public class AuthorisationController {

    private final AuthService authService;

    @PostMapping("/usersignup")
    public ResponseEntity<String> signup(@RequestBody UserRegistration userRegistration) {
        authService.signup(userRegistration);
        return new ResponseEntity<>("New User Registration Successful",
                OK);
    }


    @PostMapping("/userlogin")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        authService.login(userLoginRequest);
        return new ResponseEntity<>("New User Login Successful",
                OK);
    }

    @PostMapping("/userlogout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return new ResponseEntity<>("User Logout Successful",
                OK);


    }

}