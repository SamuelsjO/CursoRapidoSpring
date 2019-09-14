package com.backend.projeto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.projeto.entity.User;
import com.backend.projeto.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	UserRepository userRepository;
	
	@Secured({"ROLE_ADMIN" , "ROLE_ALUNO"})
	@GetMapping
	public Page<User> list(
			@RequestParam("page") int page,
			@RequestParam("size") int size
			){

		return userRepository.findAll(PageRequest.of(page, size));
	}
	
	@PostMapping
	public User save(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping
	public User update(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping(value = "/{id}")
	public Optional<User> detail(@PathVariable Long id) {
		return userRepository.findById(id);
	}
}
