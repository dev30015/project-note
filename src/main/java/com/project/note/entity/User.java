package com.project.note.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NonNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//@Data
@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="uid")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "users_roles", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	//@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Note> notes = new ArrayList<>();

	@JsonManagedReference
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Profile profile;

//	private List<SecurityQA> securityQAS=new ArrayList<>();

	/* start of user details */
	@JsonIgnore
	@Column(name = "enabled")
	private boolean enabled;

	@JsonIgnore
	@Column(name = "account_non_expired")
	private boolean accountNonExpired;

	@JsonIgnore
	@Column(name = "account_non_locked")
	private boolean accountNonLocked;

	@JsonIgnore
	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired;

	@JsonIgnore
	@Transient
	private List<GrantedAuthority> authorities;

	/* end of user details */

	public User() {
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonExpired = true;
		this.credentialsNonExpired = true;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonExpired = true;
		this.credentialsNonExpired = true;
	}

//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		for (Role role : getRoles()) {
//			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//		}
//		return authorities;
//	}

	public void addNote(Note note) {
		this.notes.add(note);
		note.setUser(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
		profile.setUser(this);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", roles=" + roles +
				", notes=" + notes +
				", profile=" + profile +
				", enabled=" + enabled +
				", accountNonExpired=" + accountNonExpired +
				", accountNonLocked=" + accountNonLocked +
				", credentialsNonExpired=" + credentialsNonExpired +
				", authorities=" + authorities +
				'}';
	}
}
