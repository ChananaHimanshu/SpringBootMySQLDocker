package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.UserService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUser() {
		return userService.getUser();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) throws NotFoundException {
		return userService.getUser(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public User saveUser(@RequestBody User user) throws NotFoundException {
		return userService.saveUser(user);
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public List<Role> getRole(@RequestParam(required = false) Long id) throws NotFoundException {
		return userService.getRole(id);
	}

}
