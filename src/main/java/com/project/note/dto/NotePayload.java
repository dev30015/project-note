package com.project.note.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Calendar;

@Data
public class NotePayload {

    @JsonProperty("content")
    private String content;

    @JsonProperty("date_posted")
    private Calendar datePosted;
}
