package com.app.bookmymovie.service;

import java.util.List;
import java.util.Optional;

import com.app.bookmymovie.pojo.Movie;


public interface IMovieService {
	
	Movie createMovie(Movie movie);
	Movie updateMovie(Movie movie);
	List<Movie> getAllMovie();
	Optional<Movie> getAllMovieById(int id);
	void deleteMovie(int id);

}
