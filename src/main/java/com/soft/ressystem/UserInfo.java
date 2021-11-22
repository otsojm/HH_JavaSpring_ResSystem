package com.soft.ressystem;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfo {
	
	public UserInfo() {
	}

	public String getUsername() {

		String username = "";

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {

			username = ((UserDetails) principal).getUsername();

		} else {

			username = principal.toString();

		}

		return username;

	}
}
