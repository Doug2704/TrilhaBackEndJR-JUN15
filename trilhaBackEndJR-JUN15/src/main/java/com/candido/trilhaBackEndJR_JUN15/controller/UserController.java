package com.candido.trilhaBackEndJR_JUN15.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.candido.trilhaBackEndJR_JUN15.entity.user.User;
import com.candido.trilhaBackEndJR_JUN15.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user/save")
	public String saveUser(@RequestBody User user) {
		try {
			User existsByName = userRepository.findByUsername(user.getUsername());
			if (existsByName != null && existsByName.getUsername().equals(user.getUsername())) {
				return "Já existe um usuário com esse nome";
			}

			userRepository.save(user);
			return "Usuário salvo";

		} catch (Exception e) {
			return "Erro ao criar usuário: " + e.getMessage();
		}

	}

	@GetMapping("/user/id/{id}")
	public Optional<User> findById(@PathVariable String id) {
		try {
			Optional<User> user = userRepository.findById(id);
			return user;
		} catch (Exception e) {
			throw new RuntimeException("erro ao buscar usuário: ", e);
		}

	}

	@GetMapping("/user/name/{username}")
	public User findByName(@PathVariable String username) {
		try {
			User user = userRepository.findByUsername(username);
			if (user == null) {
				return null;
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar usuário: ", e);
		}
	}

	@GetMapping("/user")
	public List<User> findAll() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("erro ao buscar usuário: ", e);
		}
	}

	@PostMapping("/user/update/{id}")
	public String updateUserById(@RequestBody User user) {
		try {

			userRepository.save(user);
			return "Usuário atualizado";
		} catch (Exception e) {
			return "erro ao atualizar usuário: " + e.getMessage();
		}
	}

	@PostMapping("user/delete/{id}")
	public String deleteUserById(@PathVariable String id) {
		Optional<User> user = userRepository.findById(id);
		try {
			if (user == null) {
				return "Usuário inexistente";
			}
			userRepository.deleteById(id);
			return "Usuário apagado";
		} catch (Exception e) {
			return "erro ao apagar usuário: " + e.getMessage();
		}
	}

};