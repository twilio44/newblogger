package com.blog1.payload;

import lombok.Data;

@Data
public class LoginDto {

    private  String usernameOrEmail;
    private String password;

    // In Above class we are taking username and password  which the user is entering

    // whatever username password --> user is entering --> that will go to loginDto
}
