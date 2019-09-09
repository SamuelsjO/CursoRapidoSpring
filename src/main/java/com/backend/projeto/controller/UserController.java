package com.backend.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.projeto.entity.User;
import com.backend.projeto.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "", method=RequestMethod.GET)
	public List<User> list(){
		return userRepository.findAll();
	}
}
