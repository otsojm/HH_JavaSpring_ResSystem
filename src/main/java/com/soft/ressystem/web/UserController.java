package com.soft.ressystem.web;

import com.soft.ressystem.domain.SignupForm;
import com.soft.ressystem.domain.User;
import com.soft.ressystem.domain.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;

import javax.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserRepo uRepo;

	// Login page

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	// Make new user (sign up)

	@RequestMapping("/signup")
	public String addUser(Model model) {

		model.addAttribute("signupform", new SignupForm());

		return "signup";
	}

	// Save the new user

	@PostMapping("/saveuser")
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult)
			throws IOException {

		if (!bindingResult.hasErrors()) {

			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {

				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRole("USER");
				newUser.setEmail(signupForm.getEmail());
				newUser.setCustomertype(signupForm.getCustomertype());

				if (signupForm.getCustomertype().equals("Adult")) {

					newUser.setPricecategory(8.0);

				} else {

					newUser.setPricecategory(6.0);

				}

				if (uRepo.findUserByUsername(signupForm.getUsername()) == null) {

					uRepo.save(newUser);
				} else {

					bindingResult.rejectValue("username", "err.username", "Username already exist!");
					return "signup";
				}
			}

			else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Password does not match!");
				return "signup";
			}
		}

		else {
			return "signup";

		}
		return "redirect:/login";
	}
}