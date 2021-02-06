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
import com.app.bookmymovie.pojo.Actor;
import com.app.bookmymovie.service.IAuthenticationService;
import com.app.bookmymovie.util.JwtUtil;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthenticationController {
	@Autowired
	IAuthenticationService authenticationService;
	
	@Autowired
	private JwtUtil utils;

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
		AuthenticationResponse resp = authenticationService.changePassword(oldPassword, newPassword);
		if (resp == null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

		return new ResponseEntity<>(resp,HttpStatus.OK);
	}
	@PostMapping("/validate")
	public ResponseEntity<?> getActor(@RequestParam String jwt) {
		String userName = utils.extractUsername(jwt);
		Actor actor = authenticationService.loadActorByUsername(userName);
		if(actor == null)
			return null ;
		return new ResponseEntity<>(actor,HttpStatus.OK);
	}
}
