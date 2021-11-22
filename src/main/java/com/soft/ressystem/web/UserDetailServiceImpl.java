package com.soft.ressystem.web;

import com.soft.ressystem.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soft.ressystem.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	public UserRepository uRepo;

	public UserDetailServiceImpl() {
	}
	
	public UserDetailServiceImpl(UserRepository userRepository) {
		uRepo = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User curruser = uRepo.findUserByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
		
	}
}