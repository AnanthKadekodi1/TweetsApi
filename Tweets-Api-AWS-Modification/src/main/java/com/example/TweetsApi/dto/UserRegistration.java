package com.example.TweetsApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistration {
    private String userId;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userBio;
}