package com.security.springsecuritywithdatabaseconnectivity.controllers;

import com.security.springsecuritywithdatabaseconnectivity.dtos.UserDto;
import com.security.springsecuritywithdatabaseconnectivity.dtos.UserInfoDto;
import com.security.springsecuritywithdatabaseconnectivity.services.UserInfoService;
import com.security.springsecuritywithdatabaseconnectivity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author rahul sharma
 */
@Controller
//@RequestMapping("/user")
public class UserController {

		@Autowired
		private UserInfoService userInfoService;

		@Autowired
		private UserService userService;

		@GetMapping("/hello")
		public String hello(Model model) {
				UserDetails details = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				UserDto user = userService.getUser(details.getUsername());
				model.addAttribute("info", user);
				model.addAttribute("users", userInfoService.getAllUsersByRole(user.getRole()));
				return "hello";
		}

		@GetMapping("/")
		public String home1() {
				return "home";
		}

		@GetMapping("/home")
		public String home() {
				return "home";
		}

		@GetMapping("/login")
		public String login() {
				return "login";
		}

		@GetMapping("/updateUser/{id}")
		public String updateUser(@PathVariable Long id, Model model) {
				UserInfoDto dto = null;
				try {
						dto = userInfoService.getUserInfo(id);
						model.addAttribute("user", dto);
						return "updateUser";
				} catch (Exception e) {
						model.addAttribute("message", e.getMessage());
						return hello(model);
				}
		}


		@PostMapping("/saveUser/{id}")
		public String saveUser(@PathVariable Long id, @ModelAttribute("user") UserInfoDto user, BindingResult result, Model model){
				try{
						UserDetails details = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

						UserInfoDto existingUserInfoDto = userInfoService.getUserInfo(id);
						user.setUserId(existingUserInfoDto.getUserId());
						user.setId(id);
						user.setRole(existingUserInfoDto.getRole());
						UserDto loggedInUser = userService.getUser(details.getUsername());
						userInfoService.createOrUpdate(user, loggedInUser.getRole());


						return hello(model);
				}catch (Exception e){
						model.addAttribute("user", user);
						model.addAttribute("message", e.getMessage());
						return "updateUser";
				}
		}
}
