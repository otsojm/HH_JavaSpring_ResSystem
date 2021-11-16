 package com.soft.ressystem.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<Users, Long> {
	
	Users findUserByUsername(String username);

}