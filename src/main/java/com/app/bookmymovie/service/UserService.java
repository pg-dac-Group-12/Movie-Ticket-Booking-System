package com.app.bookmymovie.service;

import com.app.bookmymovie.pojo.User;

public class UserService {
	public boolean changePassword(User user , String oldPassword , String newPassword) {
		if(oldPassword.equals(user.getPassword())) {
			user.setPassword(newPassword);
			return true ;
		}
		return false ;
	}
}
