 package com.soft.ressystem.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findUserByUsername(String username);
}
