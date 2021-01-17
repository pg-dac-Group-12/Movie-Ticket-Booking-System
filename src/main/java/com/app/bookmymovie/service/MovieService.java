package com.app.bookmymovie.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.bookmymovie.pojo.Movie;
import com.app.bookmymovie.repository.MovieRepository;

@Service
@Transactional
public class MovieService implements IMovieService {
	
	@Autowired
	MovieRepository movieRepo ;
	
	public Movie createMovie(Movie movie) {
		return movieRepo.save(movie);
		
	}

	public Movie updateMovie(int id, Movie movie) {
		Optional<Movie> movieOld = movieRepo.findById(id);
		if(!movieOld.isPresent())
			return null;
		movie.setId(id);
		return movieRepo.save(movie);
	}

	public Movie addIcon(int id, MultipartFile imageFile) {
		Movie m = movieRepo.findById(id).get();
		m.setIcon(imageFile.getName());
		m.setIconContentType(imageFile.getContentType());
		return movieRepo.save(m);
	}
	
	public List<Movie> getAllMovie() {
		return movieRepo.findAll();

	}
	
	public Optional<Movie> getAllMovieById(int id){
		return movieRepo.findById(id);
		
	}
	
	public void deleteMovie(int id) {
		movieRepo.deleteById(id);
		
	}


}
