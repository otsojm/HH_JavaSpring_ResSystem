package com.soft.ressystem;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import com.soft.ressystem.domain.User;
import com.soft.ressystem.domain.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository uRepo;

	User user = new User("test", "$2a$10$tfCTgTKBEwLJfKy8n4hkZO9FhCME.5qsfiFFhyjrevLHxPLNj5PQ.", "USER",
			"test@local.com", "Adult", 8.0);

	@Test
	public void createNewUser() {

		assertThat(user.getCustomertype()).isNotNull();
	}

	@Test
	public void findAllShouldReturnList() {

		List<User> users = uRepo.findAll();

		int size = users.size();

		assertThat(users).hasSize(size);
	}

	@Test
	public void findUserByUsernameShouldReturnUser() {

		uRepo.insert(user);

		User user = uRepo.findUserByUsername("test");
		assertThat(user.getUsername().equals("test"));
		
		uRepo.delete(user);
	}
}
