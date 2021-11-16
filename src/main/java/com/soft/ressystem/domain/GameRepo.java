package com.soft.ressystem.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends MongoRepository<Game, String> {

	List<Game> findByGametypeContaining(String gametype);	
}
