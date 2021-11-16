package com.soft.ressystem.domain;

import javax.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {
	
	@Id
	private Long userid;

	private String username, password, email, role;

	public Users() {
	}

	public Users(String username, String password, String role, String email) {

		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;

	}

	public Long getId() {
		return userid;
	}

	public void setId(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return password;
	}

	public void setPasswordHash(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
