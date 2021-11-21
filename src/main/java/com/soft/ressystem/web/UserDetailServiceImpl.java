package com.soft.ressystem.web;

import com.soft.ressystem.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soft.ressystem.domain.UserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepo uRepo;

	@Autowired
	public UserDetailServiceImpl(UserRepo userRepository) {
		uRepo = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User curruser = uRepo.findUserByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
		
	}
}