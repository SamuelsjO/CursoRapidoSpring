package com.backend.projeto.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.backend.projeto.entity.User;
import com.backend.projeto.repository.UserRepository;

@Component
public class DataInitalizer implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {
			this.createUser("Samuel","faculdadesjs@gmail","12345");
			this.createUser("Pedro","faculdadesjs@gmail","12345");
			this.createUser("Vitoria","faculdadesjs@gmail","12345");
		}
		
		
	}
 public void createUser(String name, String email, String password) {
	 User user = new User(name, email, password);
	 userRepository.save(user);
 }
}
