package com.app.bookmymovie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.service.IUserService;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService ;
	
	@PostMapping 
	public ResponseEntity<?> createUser(@RequestBody User user ) {
		user = userService.createUser(user);
		if(user == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
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
	
	@PostMapping("/password/change")
	public ResponseEntity<?> changePassword(@RequestParam String oldPassword , @RequestParam String newPassword , HttpSession session) {
		System.out.println("Auth Controller : /password/change");
		boolean passwordChanged = userService.changePassword((User) session.getAttribute("user"), oldPassword, newPassword);
		if(!passwordChanged)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
