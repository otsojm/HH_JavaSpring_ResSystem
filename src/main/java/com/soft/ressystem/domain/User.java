package com.soft.ressystem.domain;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private String id;

	private String username, password, email, role, customertype;

	private Double pricecategory;

	public User() {
	}

	public User(String username, String password, String role, String email, String customertype,
			Double pricecategory) {

		this.username = username;
		this.password = password;
		this.role = role;
		this.customertype = customertype;
		this.pricecategory = pricecategory;
		this.email = email;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setPricecategory(Double pricecategory) {
		this.pricecategory = pricecategory;
	}

	public Double getPricecategory() {
		return pricecategory;
	}

	@Override
	public String toString() {
		return "Id=" + id + ", Username=" + username + ", Password=" + password + ", Role=" + role + ", Customertype="
				+ customertype + ", Pricecategory=" + pricecategory + ", Email=" + email;
	}
}
