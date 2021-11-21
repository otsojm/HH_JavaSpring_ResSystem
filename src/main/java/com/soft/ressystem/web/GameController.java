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
import com.soft.ressystem.InfoUser;
import com.soft.ressystem.domain.VerDocumentRepo;
import com.soft.ressystem.domain.VerDocument;
import com.soft.ressystem.domain.Game;
import com.soft.ressystem.domain.GameRepo;
import com.soft.ressystem.domain.User;
import com.soft.ressystem.domain.UserRepo;

@Controller
public class GameController implements ErrorController {

	@Autowired
	public GameRepo gRepo;

	@Autowired
	public VerDocumentRepo dRepo;

	@Autowired
	public UserRepo uRepo;

	public WeatherInfo weather = new WeatherInfo();

	public String gameresId = "";

	public InfoUser infouser = new InfoUser();

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

		VerDocument document = new VerDocument();

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
		}
		return "gamelist";
	}

	// Add new

	@RequestMapping("/reservation")
	public String newRes(Model model) {

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
	public String saveRes(Game game) {

		System.out.println("Hei");

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
	public String saveEdit(Game game) {

		gRepo.deleteById(gameresId);

		gRepo.save(game);

		return "redirect:/gamelist";
	}

	// Delete

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteRes(@PathVariable("id") String gameId, Model model) {

		gRepo.deleteById(gameId);

		return "redirect:/gamelist";
	}

	// Edit

	@GetMapping("/edit/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String editRes(@PathVariable("id") String gameId, Model model) {

		model.addAttribute("game", gRepo.findById(gameId));

		gameresId = gameId;

		return "editres";
	}
}
