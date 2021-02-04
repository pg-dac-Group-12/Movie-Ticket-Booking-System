package com.app.bookmymovie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bookmymovie.pojo.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	
	Optional<Movie> findMovieById(int id);	
	List<Movie> findIconContentTypeById(int id);
	void deleteByTotalShowsLessThanEqual(int totalshows);
}
