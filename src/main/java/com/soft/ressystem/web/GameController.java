package com.soft.ressystem.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soft.ressystem.WeatherInfo;
import com.soft.ressystem.UserInfo;
import com.soft.ressystem.domain.VerificationDocumentRepository;
import com.soft.ressystem.domain.VerificationDocument;
import com.soft.ressystem.domain.Game;
import com.soft.ressystem.domain.GameRepository;
import com.soft.ressystem.domain.User;
import com.soft.ressystem.domain.UserRepository;

@Controller
public class GameController implements ErrorController {

	@Autowired
	public GameRepository gRepo;

	@Autowired
	public VerificationDocumentRepository dRepo;

	@Autowired
	public UserRepository uRepo;

	public WeatherInfo weather = new WeatherInfo();

	public String gameresId = "";

	public UserInfo infouser = new UserInfo();

	// Error page

	@RequestMapping("/error")
	public String error(Model model) {

		return "error";
	}

	// Listing

	@RequestMapping("/gamelist")
	public String list(Model model) throws InterruptedException {

		String username = infouser.getUsername();

		LocalDateTime date = LocalDateTime.now();

		model.addAttribute("weathers", weather.getWeather());

		VerificationDocument document = new VerificationDocument();

		model.addAttribute("document", document);

		ArrayList<Game> games = new ArrayList<Game>();

		if (gRepo.findAll().size() > 0) {

			for (int i = 0; i < gRepo.findAll().size(); i++) {

				String str = gRepo.findAll().get(i).getTime();

				String strParsed = str.split("T")[0] + " " + str.split("T")[1];

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

				LocalDateTime dateTime = LocalDateTime.parse(strParsed, formatter);

				if (!dateTime.isAfter(date)) {

					gRepo.delete(gRepo.findAll().get(i));

				} else {

					if (username.equals("admin")) {

						games.add(gRepo.findAll().get(i));

					} else {

						if (gRepo.findAll().get(i).getUsername().equals(username)) {

							games.add(gRepo.findAll().get(i));

						}
					}
				}
			}
			model.addAttribute("games", games);

			model.addAttribute("users", uRepo.findAll());
		}
		return "gamelist";
	}

	// Add new

	@RequestMapping("/reservation")
	public String newReservation(Model model) {

		String username = infouser.getUsername();

		Game game = new Game();

		User user = new User();

		user = uRepo.findUserByUsername(username);

		game.setPricecategory(user.getPricecategory());

		game.setCustomertype(user.getCustomertype());

		game.setUsername(user.getUsername());

		model.addAttribute("game", game);

		return "reservation";
	}

	// Save new

	@PostMapping("/save")
	public String saveReservation(Game game) {

		String username = infouser.getUsername();

		User user = new User();

		user = uRepo.findUserByUsername(username);

		game.setPricecategory(user.getPricecategory());

		game.setCustomertype(user.getCustomertype());

		game.setUsername(user.getUsername());

		gRepo.insert(game);

		return "redirect:gamelist";
	}

	// Save edit

	@PostMapping("/editsave")
	@PreAuthorize("hasRole('ADMIN')")
	public String saveEdit(Game game) {

		gRepo.deleteById(gameresId);

		gRepo.save(game);

		return "redirect:/gamelist";
	}

	// Delete

	@GetMapping("/delete/{id}")
	public String deleteReservation(@PathVariable("id") String gameId) {

		gRepo.deleteById(gameId);

		return "redirect:/gamelist";
	}

	// Edit

	@GetMapping("/edit/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editReservation(@PathVariable("id") String gameId, Model model) {

		model.addAttribute("game", gRepo.findById(gameId));

		gameresId = gameId;

		return "editres";
	}
}
