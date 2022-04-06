package com.project.note.controller;

import com.project.note.dto.ProfilePayload;
import com.project.note.entity.Profile;
import com.project.note.exception.UserNotFoundException;
import com.project.note.repository.ProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProfileController {

    private final ProfileRepository profileRespo;

    public ProfileController(ProfileRepository profileRespo) {
        this.profileRespo = profileRespo;
    }

    @GetMapping("/users/{id}/profile")
    Optional<Profile> getUserProfile(@PathVariable long id) {
        return profileRespo.findProfileByUserId(id);
    }

    @PutMapping("/users/{id}/profile")
    Profile saveUserProfile(@RequestBody ProfilePayload payload, @PathVariable Long id) {
        return profileRespo.findProfileByUserId(id).map(profile -> {
                    profile.setCountry(payload.getCountry());
                    profile.setLanguage(payload.getLanguage());
                    profile.setPhoneNumber(payload.getPhoneNumber());
                    return profileRespo.save(profile);
                }) .orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
    }
}
