package com.app.bookmymovie.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.service.IAuthenticationService;

@RestController
public class AuthenticationController {
	@Autowired
	IAuthenticationService authenticationService ;
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestParam String email,@RequestParam String password , HttpSession session)
	{
		System.out.println("Authentication Controller : /login");
		Optional<User> user = authenticationService.authenticateUser(email, password);
		if (!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		session.setAttribute("user", user.get());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
