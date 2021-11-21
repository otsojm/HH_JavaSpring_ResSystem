package com.soft.ressystem;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class InfoUser {
	
	public InfoUser() {
	}

	public String getUsername() {

		String username = "";

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {

			username = ((UserDetails) principal).getUsername();

		} else {

			username = principal.toString();

			System.out.println(username);
		}

		return username;

	}

	public String getUser() throws InterruptedException {

		String username = "";

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {

			username = ((UserDetails) principal).getUsername();

			System.out.println(username);

		} else {

			username = principal.toString();

			System.out.println(username);
		}

		return username;

	}
}
