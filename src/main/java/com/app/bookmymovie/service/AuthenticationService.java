package com.app.bookmymovie.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.repository.UserRepository;
@Service
@Transactional
public class AuthenticationService implements IAuthenticationService {
	@Autowired
	UserRepository userRepo ;
	@Override
	public Optional<User> authenticateUser(String email, String password) {
		Optional<User> user = userRepo.findByEmailAndPassword(email, password);
		return user;
	}
	
}