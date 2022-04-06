package com.project.note.dto;

import lombok.Data;

@Data
public class UserRegistration {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String country;
}
