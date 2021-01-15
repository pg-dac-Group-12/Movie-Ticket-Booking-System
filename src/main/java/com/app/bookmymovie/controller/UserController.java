package com.app.bookmymovie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping("/password/change")
	public ResponseEntity<?> changePassword(@RequestParam String oldPassword , @RequestParam String newPassword , HttpSession session) {
		System.out.println("Auth Controller : /password/change");
		boolean passwordChanged = userService.changePassword((User) session.getAttribute("user"), oldPassword, newPassword);
		if(!passwordChanged)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
