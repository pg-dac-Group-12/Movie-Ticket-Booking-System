package com.app.bookmymovie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bookmymovie.pojo.User;

public interface UserRepository extends JpaRepository<User, Integer>  {
	Optional<User> findByEmailAndPassword(String email,String password);
}
