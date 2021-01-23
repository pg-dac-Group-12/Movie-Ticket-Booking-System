package com.app.bookmymovie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
	@Autowired
	IUserService userService ;
	
	@PostMapping 
	/* @Authorize("user") */
	public ResponseEntity<?> createUser(@RequestBody User user ) {
		user = userService.createUser(user);
		System.out.println("In User");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getUser(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id) {
		user = userService.updateUser(user,id);
		if(user == null)
			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
