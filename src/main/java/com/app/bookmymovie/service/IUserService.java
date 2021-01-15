package com.app.bookmymovie.service;

import com.app.bookmymovie.pojo.User;

public interface IUserService {
	User createUser(User user);
	User updateUser(User user,int id);
	void deleteUser(int id);
	boolean changePassword(User user, String oldPassword, String newPassword);
}
