package com.app.bookmymovie.service;

import java.util.Optional;


import com.app.bookmymovie.pojo.User;

public interface IAuthenticationService {
	Optional<User> authenticateUser(String email , String password);
}
