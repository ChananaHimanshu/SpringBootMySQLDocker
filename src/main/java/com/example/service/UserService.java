package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Role;
import com.example.entity.User;
import com.example.repo.RoleRepository;
import com.example.repo.UserRepository;

import javassist.NotFoundException;

@Service
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public List<User> getUser() {
		return this.userRepository.findAll();
	}

	public User getUserByUserName(String username) {
		return this.userRepository.getUserByUserName(username);
	}

	public User getUser(Long id) throws NotFoundException {
		Optional<User> user = this.userRepository.findById(id);
		if (!user.isPresent()) {
			throw new NotFoundException("User not found");
		}
		return user.get();
	}

	public User saveUser(User user) throws NotFoundException {
		if (user.getId() != null) {
			User userDb = getUser(user.getId());
			userDb.setFirstName(user.getFirstName());
			userDb.setLastName(user.getLastName());
			if (user.getRoles() != null || !user.getRoles().isEmpty()) {
				List<Role> newRoles = new ArrayList<>();
				user.getRoles().stream().forEach(role -> {
					newRoles.add(roleRepository.getRoleByRoleName(role.getRoleName()));
				});
				userDb.setRoles(newRoles);
			}
			userDb = userRepository.save(userDb);
			return userDb;
		}
		if (user.getRoles() != null || !user.getRoles().isEmpty()) {
			List<Role> newRoles = new ArrayList<>();
			user.getRoles().stream().forEach(role -> {
				newRoles.add(roleRepository.getRoleByRoleName(role.getRoleName()));
			});
			user.setRoles(newRoles);
		}
		user = userRepository.save(user);
		return user;
	}

	public List<Role> getRole(Long id) throws NotFoundException {
		if (Objects.isNull(id)) {
			return this.roleRepository.findAll();
		}
		Optional<Role> role = this.roleRepository.findById(id);
		if (Objects.isNull(role) || !role.isPresent()) {
			throw new NotFoundException("Role not found");
		}
		return Arrays.asList(role.get());
	}

}
