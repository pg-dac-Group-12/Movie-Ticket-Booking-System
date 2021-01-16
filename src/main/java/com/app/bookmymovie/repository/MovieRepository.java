package com.app.bookmymovie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.bookmymovie.pojo.Movie;
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	
	Optional<Movie> findMovieById(int id);	
	
	
	
}
