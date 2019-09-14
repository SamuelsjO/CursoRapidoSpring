package com.backend.projeto.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.backend.projeto.entity.Role;
import com.backend.projeto.entity.User;
import com.backend.projeto.repository.RoleRepository;
import com.backend.projeto.repository.UserRepository;

@Component
public class DataInitalizer implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) {
			this.createUser("Samuel","samuel@gmail",encoder.encode("123456"), "ROLE_ALUNO");
			this.createUser("Pedro","admin@gmail",encoder.encode("123456"), "ROLE_ADMIN");
			
		}
		
		
	}
 public void createUser(String name, String email, String password, String role) {
	 
	 Role roleObjetc = new Role();
	 roleObjetc.setName(role);
	 
	 this.roleRepository.save(roleObjetc);
	 
	 User user = new User(name, email, password, Arrays.asList(roleObjetc));
	 userRepository.save(user);
 }
}
