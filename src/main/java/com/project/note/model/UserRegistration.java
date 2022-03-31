package com.project.note.model;

import lombok.Data;

@Data
public class UserRegistration {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String country;
}
