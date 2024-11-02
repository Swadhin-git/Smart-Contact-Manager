package com.bytebuddy.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bytebuddy.scm.entity.User;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-Smart Contact Manaeger");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About-Smart Contact Manaeger");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Signup-Smart Contact Manaeger");
		model.addAttribute("user", new User());
		return "signup";
	}

//handler for registering user
	@PostMapping("/do_register")
	public String registerUser(@ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model) {
		System.out.println("Aggrement " + agreement);
		System.out.println("User" + user);
		return "signup";
	}
}
