package com.soft.ressystem;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import com.soft.ressystem.domain.VerificationDocument;
import com.soft.ressystem.domain.VerificationDocumentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VerificationDocumentRepositoryTest {

	@Autowired
	private VerificationDocumentRepository vRepo;

	@Test
	public void findAllShouldReturnList() {

		List<VerificationDocument> documents = vRepo.findAll();

		int size = documents.size();

		assertThat(documents).hasSize(size);
	}

	@Test
	public void deleteDocument() {

		List<VerificationDocument> documents = vRepo.findAll();

		int size = documents.size();

		if (size > 0) {

			String id = vRepo.findAll().get(0).getId();

			vRepo.deleteById(id);

			assertThat(vRepo.findById(id).isEmpty());
		}
	}
}
