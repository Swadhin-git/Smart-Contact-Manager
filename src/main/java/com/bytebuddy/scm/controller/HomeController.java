package com.bytebuddy.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bytebuddy.scm.entity.User;
import com.bytebuddy.scm.helper.Message;
import com.bytebuddy.scm.repository.UserRepository;

import aj.org.objectweb.asm.Type;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

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
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			 RedirectAttributes redirectAttributes) {
		try {
			if (!agreement) {
				System.out.println("you have not check the agrement");
				throw new Exception("somthing went worng");
			}
			if (bindingResult.hasErrors()) {
				System.out.println("Error"+bindingResult.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			user.setRole("ROLE_USER");
			user.setEnable(true);
			System.out.println("Aggrement " + agreement);
			System.out.println("User" + user);
			User savedUser = userRepository.save(user);
			model.addAttribute("user", new User());
			redirectAttributes.addFlashAttribute("message", new Message("Successfully Registered !!", "alert-sucess"));
			return "redirect:/signup";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			redirectAttributes.addFlashAttribute("message",
					new Message("Somthing went worng " + e.getLocalizedMessage(), "alert-danger"));
			return "redirect:/signup";
		}
	}
}
