package com.project.note.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

//@Data
@Entity
@Table(name = "role")
//@JsonIgnoreProperties(value = { "users" })
public class Role {

	@Id
	@GeneratedValue
	@Column(name = "rid")
	private long rid;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_description")
	private String roleDescription;

	// @ManyToMany(targetEntity=User.class,mappedBy="roles",fetch = FetchType.EAGER)
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@ManyToMany(targetEntity = User.class, mappedBy = "roles")
	//@ManyToMany(targetEntity=User.class,mappedBy="roles",fetch = FetchType.EAGER)
	//@JsonBackReference
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role() {

	}

	public Role(String roleName, String roleDescription) {
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}
	

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void addUser(User user) {

		if (this.users == null) {
			this.users = new HashSet<User>();
		}

		this.users.add(user);
	}

	@Override
	public String toString() {
		return "Role{" +
				"rid=" + rid +
				", roleName='" + roleName + '\'' +
				", roleDescription='" + roleDescription + '\'' +
				", users=" + users +
				'}';
	}
}