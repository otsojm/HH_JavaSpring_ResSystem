package com.soft.ressystem.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.soft.ressystem.domain.Game;
import com.soft.ressystem.domain.GameRepo;

@Controller
public class GameController implements ErrorController {

	@Autowired
	public GameRepo gRepo;

	public WeatherInfo weather = new WeatherInfo();

	// Error page

	@RequestMapping("/error")
	public String error(Model model) {

		return "error";
	}

	// Listing

	@RequestMapping("/gamelist")
	public String list(Model model) throws InterruptedException {

		LocalDateTime date = LocalDateTime.now();

		System.out.println(gRepo.findAll().size());

		if (gRepo.findAll().size() > 0) {

			for (int i = 0; i < gRepo.findAll().size(); i++) {

				String str = gRepo.findAll().get(i).getTime();

				String strParsed = str.split("T")[0] + " " + str.split("T")[1];

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

				LocalDateTime dateTime = LocalDateTime.parse(strParsed, formatter);

				if (!dateTime.isAfter(date)) {

					gRepo.delete(gRepo.findAll().get(i));

				} else {

					model.addAttribute("games", gRepo.findAll());

				}

				model.addAttribute("weathers", weather.getWeather());

			}
		}
		return "gamelist";
	}

	// Add new

	@RequestMapping("/reservation")
	public String newRes(Model model) {

		model.addAttribute("game", new Game());

		return "reservation";
	}

	// Save new

	@PostMapping("/save")
	public String saveRes(Game game) {

		gRepo.insert(game);

		return "redirect:gamelist";
	}

	// Save edit

	@PostMapping("/editsave")
	public String saveEdit(Game game) {

		System.out.println(game.getId());

		gRepo.save(game);

		return "redirect:gamelist";
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

		return "editres";
	}
}
