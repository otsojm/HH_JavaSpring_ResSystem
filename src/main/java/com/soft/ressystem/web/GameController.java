package com.soft.ressystem.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soft.ressystem.WeatherInfo;
import com.soft.ressystem.UserInfo;
import com.soft.ressystem.domain.VerificationDocument;
import com.soft.ressystem.domain.VerificationDocumentRepository;
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

	public String gameresId = "";
	public String username = "";

	public UserInfo infouser = new UserInfo();
	public WeatherInfo weather = new WeatherInfo();
	public Game game = new Game();
	public User user = new User();

	public LocalDateTime date = LocalDateTime.now();
	public LocalDateTime dateTime = LocalDateTime.now();
	public ArrayList<Integer> sameGametype = new ArrayList<Integer>();

	public Double hour = 0.0;
	public Double hourNew = 0.0;

	public Double minutes = 0.0;
	public Double minutesNew = 0.0;

	public Double milliseconds = 0.0;
	public Double millisecondsNew = 0.0;

	// Error page

	@RequestMapping("/error")
	public String error(Model model) {

		return "error";
	}

	// Listing

	@RequestMapping("/gamelist")
	public String list(Model model) throws InterruptedException {

		username = infouser.getUsername();

		model.addAttribute("weathers", weather.getWeather());

		VerificationDocument document = new VerificationDocument();

		model.addAttribute("document", document);

		ArrayList<Game> games = new ArrayList<Game>();

		model.addAttribute("users", uRepo.findAll());

		if (gRepo.findAll().size() > 0) {

			for (int i = 0; i < gRepo.findAll().size(); i++) {

				String str = gRepo.findAll().get(i).getTime();

				String strParsed = str.split("T")[0] + " " + str.split("T")[1];

				dateTime = LocalDateTime.parse(strParsed, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

				if (deleteOldReservation(dateTime) == true) {
					gRepo.delete(gRepo.findAll().get(i));

				} else if (gRepo.findAll().get(i).getUsername().equals(username) || username.equals("admin")) {

					games.add(gRepo.findAll().get(i));
				}
			}

			model.addAttribute("games", games);
		}

		return "gamelist";
	}

	// Add new

	@RequestMapping("/reservation")
	@PreAuthorize("hasRole('USER')")
	public String reservationCheck(Model model) {

		username = infouser.getUsername();

		user = uRepo.findUserByUsername(username);

		game.setPricecategory(user.getPricecategory());

		game.setCustomertype(user.getCustomertype());

		game.setUsername(user.getUsername());

		model.addAttribute("game", game);

		model.addAttribute("games", gRepo.findAll());

		return "reservation";
	}

	// Save new

	@PostMapping("/save")
	public String saveReservation(Game game, BindingResult bindingResult, Model model) {

		for (int i = 0; i < gRepo.findAll().size(); i++) {

			if (game.getGametype().equals(gRepo.findAll().get(i).getGametype())) {

				sameGametype.add(i);
			}
		}

		if (reservationCheck(sameGametype, game) == true) {

			username = infouser.getUsername();

			user = uRepo.findUserByUsername(username);

			game.setPricecategory(user.getPricecategory());

			game.setCustomertype(user.getCustomertype());

			game.setUsername(user.getUsername());

			gRepo.insert(game);

			gRepo.deleteById(gameresId);

			return "redirect:gamelist";

		} else {

			if (bindingResult.hasFieldErrors("time")) {

				bindingResult.rejectValue("time", "err.time", "Overlapping reservation exist!");

				model.addAttribute("game", game);
				model.addAttribute("games", gRepo.findAll());

				return "redirect:reservation";

			} else {

				bindingResult.rejectValue("gametype", "err.gametype", "Overlapping reservation exist!");

				return "error";

			}
		}
	}

	// Save edit

	@PostMapping("/editsave")
	@PreAuthorize("hasRole('ADMIN')")
	public String saveEdit(Game game) {

		game.setUsername(gRepo.findById(gameresId).get().getUsername());

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

		model.addAttribute("games", gRepo.findAll());

		gameresId = gameId;

		return "editres";
	}

	// Does similar reservation exist

	public boolean reservationCheck(ArrayList<Integer> sameGametype, Game game) {

		date = LocalDateTime.now();

		String str = game.getTime();

		String strParsed = str.split("T")[0] + " " + str.split("T")[1];

		dateTime = LocalDateTime.parse(strParsed, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		ArrayList<Boolean> overlaps = new ArrayList<Boolean>();

		if (dateTime.isBefore(date)) {

			return false;
		}

		else if (gRepo.findAll().size() < 1 || sameGametype.size() < 1) {

			return true;
		}

		for (int i = 0; i < sameGametype.size(); i++) {

			hour = Double.parseDouble(gRepo.findAll().get(sameGametype.get(i)).getTime().split("T")[1].split(":")[0])
					* 3600000;
			hourNew = Double.parseDouble(game.getTime().split("T")[1].split(":")[0]) * 3600000;

			minutes = (Double.parseDouble(gRepo.findAll().get(sameGametype.get(i)).getTime().split(":")[1]) / 60)
					* 3600000;
			minutesNew = (Double.parseDouble(game.getTime().split("T")[1].split(":")[1]) / 60) * 3600000;

			milliseconds = hour + minutes;
			millisecondsNew = hourNew + minutesNew;

			if (millisecondsNew - milliseconds >= 3600000 || millisecondsNew - milliseconds <= -3600000) {

				overlaps.add(true);

			} else {

				if (gRepo.findAll().get(sameGametype.get(i)).getGametype().equals(game.getGametype())) {

					overlaps.add(false);

				}
			}
		}

		if (!overlaps.contains(false)) {

			return true;
		}

		return false;
	}

	// Delete old reservations

	public boolean deleteOldReservation(LocalDateTime dateTime) {

		date = LocalDateTime.now();

		if (date.getDayOfMonth() > dateTime.getDayOfMonth() || date.getMonthValue() > dateTime.getMonthValue()
				|| date.getYear() > dateTime.getYear()) {

			return true;

		} else {

			for (int i = 0; i < gRepo.findAll().size(); i++) {

				hour = Double.parseDouble(date.toString().split("T")[1].split(":")[0]) * 3600000;
				hourNew = Double.parseDouble(dateTime.toString().split("T")[1].split(":")[0]) * 3600000;

				minutes = (Double.parseDouble(date.toString().split(":")[1]) / 60) * 3600000;
				minutesNew = (Double.parseDouble(dateTime.toString().split("T")[1].split(":")[1]) / 60) * 3600000;

				milliseconds = hour + minutes;
				millisecondsNew = hourNew + minutesNew;

				if (millisecondsNew - milliseconds <= -3600000) {

					return true;
				}
			}
		}

		return false;
	}
}
