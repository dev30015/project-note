package com.project.note.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProfilePayload {
    @JsonProperty("profile_image_link")
    private String profileImageLink;

    @JsonProperty("country")
    private String country;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("language")
    private String language;
}