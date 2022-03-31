package com.project.note.controller;

import com.project.note.entity.Profile;
import com.project.note.repository.ProfileRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private final ProfileRepository profileRespo;

    public ProfileController(ProfileRepository profileRespo) {
        this.profileRespo = profileRespo;
    }
    /*
    @GetMapping("/users/{id}/profile")
    Profile getUserProfile(@PathVariable long id) {

    }

     */
}
