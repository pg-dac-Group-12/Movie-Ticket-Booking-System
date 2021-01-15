package com.app.bookmymovie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.bookmymovie.pojo.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

	@Query("select u from User u where u.email=:em and u.password=:pass")
	Optional<User> findByEmailIdAndName(@Param("em") String email,@Param("pass") String password);
}
