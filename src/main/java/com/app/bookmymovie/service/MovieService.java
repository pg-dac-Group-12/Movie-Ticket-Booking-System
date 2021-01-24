package com.app.bookmymovie.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
	
	@Override
	public Movie createMovie(Movie movie) {
		return movieRepo.save(movie);
		
	}
	@Override
	public Movie updateMovie(int id, Movie movie) {
		Optional<Movie> movieOld = movieRepo.findById(id);
		if(!movieOld.isPresent())
			return null;
		movie.setId(id);
		return movieRepo.save(movie);
	}
	@Override 
	public Movie addIcon(int id, MultipartFile imageFile) {
		
		File file = new File("/home/jatin/Desktop/"+id+"/");
		file.mkdir();
        String fileName = imageFile.getOriginalFilename();
        String filePath = file + "/" + fileName;
       
        	try {
				Files.copy(imageFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e) {
						System.out.println("error");
				}
		Movie m = movieRepo.findById(id).get();
		m.setIconContentType(imageFile.getContentType());
		
		m.setIcon(imageFile.getOriginalFilename());
		return movieRepo.save(m);
	}
	
	@Override
	public List<Movie> getAllMovie() {
		return movieRepo.findAll();

	}
	@Override
	public Optional<Movie> getAllMovieById(int id){
		return movieRepo.findById(id);
		
	}

	@Override
	public void deleteUnscreenedMovies() {
		movieRepo.deleteByTotalShowsLessThanEqual(0);
	}
	@Override
	public void deleteMovie(int id) {
		movieRepo.deleteById(id);	
	}


}
