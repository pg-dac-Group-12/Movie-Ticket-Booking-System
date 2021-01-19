package com.app.bookmymovie.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	UserRepository userRepo ;

	@Override
	public User createUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user, int id) {
		Optional<User> userOld = userRepo.findById(id);
		if(!userOld.isPresent())
			return null;
		user.setId(userOld.get().getId());
		return userRepo.save(user);
		
	}

	
	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}
}
