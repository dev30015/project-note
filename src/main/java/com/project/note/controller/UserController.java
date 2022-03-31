package com.project.note.controller;

import com.project.note.entity.Profile;
import com.project.note.entity.User;
import com.project.note.exception.UserNotFoundException;
import com.project.note.model.UserRegistration;
import com.project.note.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    UserController(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @GetMapping("/isUsernameRegistered")
    boolean checkUserRegistered(@RequestParam String username) {
        if(repository.findByUsername(username)!=null) {
            return true;
        }
        return false;
    }

    @GetMapping("/users/{id}/profile")
    Profile getUserProfile(@PathVariable Long id) {
        try {
            return repository.findById(id).get().getProfile();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException(String.valueOf(id));
        }
    }

    @PostMapping("/register")
    User registerUser(@RequestBody UserRegistration registrationUser) {
        User newUser = new User();
        Profile newProfile = new Profile();
        BeanUtils.copyProperties(registrationUser,newUser);
        System.out.println(registrationUser);
        System.out.println(newUser);
        newUser.setPassword(bCryptPasswordEncoder.encode(registrationUser.getPassword()));
        newProfile.setCountry(registrationUser.getCountry());
        newProfile.setPhoneNumber(registrationUser.getPhoneNumber());
        newUser.setProfile(newProfile);
        return repository.save(newUser);
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        /*
        Full Update
        User user = new User();
        BeanUtils.copyProperties(user,newUser);
        return repository.save(user);
        */
        //Partial Update
        return repository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
                    user.setNotes(newUser.getNotes());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
