package com.soft.ressystem.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignupForm {
	@NotEmpty
	@Size(min = 4, max = 30)
	private String username = "";

	@NotEmpty
	@Size(min = 4, max = 30)
	private String password = "";

	@NotEmpty
	@Size(min = 4, max = 30)
	private String passwordCheck = "";

	@NotEmpty
	@Size(min = 5, max = 30)
	private String email = "";

	@NotEmpty
	@Size(min = 5, max = 30)
	private String customertype = "";

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

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getCustomertype() {
		return customertype;
	}
}
