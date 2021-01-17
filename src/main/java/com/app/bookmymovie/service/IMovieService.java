package com.app.bookmymovie.service;

import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

import com.app.bookmymovie.pojo.Movie;


public interface IMovieService {
	
	Movie createMovie(Movie movie);
	Movie addIcon(int id, MultipartFile imageFile);
	Movie updateMovie(int id, Movie movie);
	List<Movie> getAllMovie();
	Optional<Movie> getAllMovieById(int id);
	void deleteMovie(int id);

}
