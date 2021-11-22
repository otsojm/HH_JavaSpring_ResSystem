package com.soft.ressystem.domain;

import javax.persistence.*;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documents")
public class VerificationDocument {

	@Id
	private String id;

	private Binary file;

	public VerificationDocument() {
	}

	public VerificationDocument(Binary file) {

		this.file = file;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Binary getFile() {
		return file;
	}

	public void setFile(Binary binary) {
		this.file = binary;
	}

	@Override
	public String toString() {
		return "Id=" + id + ", Binary=" + file;
	}
}
