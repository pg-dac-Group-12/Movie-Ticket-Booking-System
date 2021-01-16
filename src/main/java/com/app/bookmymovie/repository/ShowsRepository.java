package com.app.bookmymovie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bookmymovie.pojo.Shows;

public interface ShowsRepository extends JpaRepository<Shows,Integer>{
	Optional<Shows> findAllByMovieId(int id);
	
}
