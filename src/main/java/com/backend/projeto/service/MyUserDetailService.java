package com.backend.projeto.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.projeto.entity.User;
import com.backend.projeto.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

	private final UserRepository userRepostory;
	
	@Autowired
	public MyUserDetailService(UserRepository userRepository) {
		this.userRepostory = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepostory.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Usuario ou senha incorreta");
		}
		return new UserRepositoryUserDetails(user);
	}

	private final static class UserRepositoryUserDetails extends User implements UserDetails{

		private UserRepositoryUserDetails(User user) {
			super(user);
		}
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			return getRoles();
		}

		@Override
		public String getUsername() {
			
			return this.getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			
			return true;
		}

		@Override
		public boolean isEnabled() {
			
			return true;
		}
		@Override
		public String getPassword() {
			return super.getPassword();
		}
		
	}
}
