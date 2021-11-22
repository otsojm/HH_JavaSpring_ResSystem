package com.soft.ressystem;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import com.soft.ressystem.domain.Game;
import com.soft.ressystem.domain.GameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {

	@Autowired
	private GameRepository gRepo;

	Game game = new Game("Tennis", "2022-12-11T00:56", "Adult", 8.0, "Tennis Indoor", "user");

	@Test
	public void createNewGame() {

		assertThat(game.getGametype()).isNotNull();
	}

	@Test
	public void findByGametypeShouldReturnGames() {

		List<Game> games = gRepo.findByGametypeContaining("Tennis");

		System.out.println(games);

		assertThat(!games.isEmpty());
	}

	@Test
	public void findAllShouldReturnList() {

		List<Game> games = gRepo.findAll();

		int size = games.size();
		
		assertThat(games).hasSize(size);
	}

	@Test
	public void deleteGame() {

		gRepo.insert(game);

		int size = gRepo.findAll().size();

		String id = gRepo.findAll().get(size - 1).getId();

		gRepo.delete(game);

		assertThat(gRepo.findById(id)).isEmpty();
	}
}
