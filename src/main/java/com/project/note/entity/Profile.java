package com.project.note.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="profile")
public class Profile {

    @Id
    @GeneratedValue
    @Column(name="pid")
    private Long profileID;

    @Column(name="profile_image_link")
    private String profileImageLink;

    @Column(name="country")
    private String country;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="language")
    private String language;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uid")
    private User user;

    public Profile() {
    }

    public Profile(String country, String language) {
        this.country = country;
        this.language = language;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getProfileID() {
        return profileID;
    }

    public void setProfileID(Long profileID) {
        this.profileID = profileID;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(profileID, profile.profileID) && Objects.equals(profileImageLink, profile.profileImageLink) && Objects.equals(country, profile.country) && Objects.equals(language, profile.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileID, profileImageLink, country, language);
    }
}
