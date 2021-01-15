package com.app.bookmymovie.service;

import com.app.bookmymovie.pojo.User;

public interface IUserService {
	boolean changePassword(User user, String oldPassword, String newPassword);
}
