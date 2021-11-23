package com.soft.ressystem.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soft.ressystem.domain.Game;
import com.soft.ressystem.domain.GameRepository;
import com.soft.ressystem.domain.UserRepository;

@RestController
public class RestCont implements ErrorController {

	@Autowired
	public GameRepository gRepo;

	@Autowired
	public UserRepository cRepo;

	// All games REST

	@GetMapping("/games")
	public @ResponseBody List<Game> gameListRest() {

		return (List<Game>) gRepo.findAll();
	}

	// One book REST

	@GetMapping("game/{id}")
	public @ResponseBody Optional<Game> findGameRest(@PathVariable("id") String id) {

		return gRepo.findById(id);
	}

	// Delete by REST

	@DeleteMapping("game/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteBookRest(@PathVariable("id") String id) {

		gRepo.deleteById(id);
	}

	// Add by REST

	@PostMapping("/game")
	@PreAuthorize("hasRole('ADMIN')")
	public Game addGame(@RequestBody Game game) {

		return gRepo.insert(game);
	}

	@PutMapping("/game/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<Object> updateGame(@RequestBody Game Game, @PathVariable String id) {

		return gRepo.findById(id).map(game -> {
			game.setGametype(Game.getGametype());
			game.setTime(Game.getTime());
			game.setCustomertype(Game.getCustomertype());
			game.setPricecategory(Game.getPricecategory());
			game.setUsername(Game.getUsername());

			return gRepo.save(game);
		});
	}
}