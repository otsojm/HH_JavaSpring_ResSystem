package com.soft.ressystem.domain;

import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationDocumentRepository extends MongoRepository<VerificationDocument, String> {

	List<VerificationDocument> findByFileContaining(Binary file);
}
