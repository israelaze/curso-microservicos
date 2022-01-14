package com.devsuperior.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClients;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClients feignClients;

	public User findByEmail(String email) {

		User user = feignClients.findByEmail(email).getBody();

		if (user == null) {

			logger.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");

		}

		logger.info("Email found: " + email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = feignClients.findByEmail(username).getBody();

		if (user == null) {

			logger.error("Email not found: " + username);
			throw new UsernameNotFoundException("Email not found");

		}

		logger.info("Email found: " + username);
		return user;
	}

}
