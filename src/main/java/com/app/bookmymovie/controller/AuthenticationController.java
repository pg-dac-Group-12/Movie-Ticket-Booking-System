package com.app.bookmymovie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.dto.AuthenticationRequest;
import com.app.bookmymovie.dto.AuthenticationResponse;
import com.app.bookmymovie.service.IAuthenticationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
	@Autowired
	IAuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateActor(@RequestBody AuthenticationRequest req) {
		return new ResponseEntity<>(authenticationService.authenticateActor(req), HttpStatus.OK);
	}
	
	@GetMapping("/logoff")
	public ResponseEntity<?> logOffUser(HttpSession session) {
		return new ResponseEntity<>(HttpStatus.OK); //Remove JWT from frontend 
	}
	
	@PostMapping("/password/change")
	public ResponseEntity<?> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
		System.out.println("Auth Controller : /password/change");
	/*	boolean passwordChanged = false;
		if (session.getAttribute("role") == null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		if (session.getAttribute("role").equals("user")) {
			passwordChanged = authenticationService.changePassword((User) session.getAttribute("user"),
					oldPassword, newPassword);
		} else if (session.getAttribute("role").equals("TheatreAdmin")) {
			passwordChanged = authenticationService.changePassword((Theatre) session.getAttribute("user"),
					oldPassword, newPassword);
		}*/
		AuthenticationResponse resp = authenticationService.changePassword(oldPassword, newPassword);
		if (resp == null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<>(resp,HttpStatus.OK);
	}
}
