package com.soft.ressystem.domain;

import javax.persistence.*;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documents")
public class VerDocument {

	@Id
	private String id;

	private Binary file;

	public VerDocument() {
	}

	public VerDocument(Binary file) {

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
