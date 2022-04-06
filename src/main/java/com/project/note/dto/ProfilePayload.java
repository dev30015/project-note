package com.project.note.dto;

import lombok.Data;

@Data
public class ProfilePayload {
    private String profileImageLink;
    private String country;
    private String phoneNumber;
    private String language;
}