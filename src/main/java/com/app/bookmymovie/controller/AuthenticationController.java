package com.app.bookmymovie.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.annotation.Authorize;
import com.app.bookmymovie.pojo.Theatre;
import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.service.IAuthenticationService;

@RestController
public class AuthenticationController {
	@Autowired
	IAuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestParam String email, @RequestParam String password,
			@RequestParam(defaultValue = "false") boolean isTheatreAdmin, HttpSession session) {
		System.out.println("Authentication Controller : /login");
		if (isTheatreAdmin) {
			Optional<Theatre> theatreAdmin = authenticationService.authenticateTheatreAdmin(email, password);
			session.setAttribute("role", "TheatreAdmin");
			if (!theatreAdmin.isPresent())
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			session.setAttribute("user", theatreAdmin.get());
		} else {
			Optional<User> user = authenticationService.authenticateUser(email, password);
			session.setAttribute("role", "user");
			if (!user.isPresent())
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			session.setAttribute("user", user.get());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/password/change")
	public ResponseEntity<?> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword,
			HttpSession session) {
		System.out.println("Auth Controller : /password/change");
		boolean passwordChanged = false;
		if (session.getAttribute("role") == null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		if (session.getAttribute("role").equals("user")) {
			passwordChanged = authenticationService.changePassword((User) session.getAttribute("user"),
					oldPassword, newPassword);
		} else if (session.getAttribute("role").equals("TheatreAdmin")) {
			passwordChanged = authenticationService.changePassword((Theatre) session.getAttribute("user"),
					oldPassword, newPassword);
		}
		if (!passwordChanged)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
