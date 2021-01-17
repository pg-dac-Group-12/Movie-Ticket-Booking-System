package com.app.bookmymovie.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.bookmymovie.dto.ResponseDTO;
import com.app.bookmymovie.pojo.Movie;
import com.app.bookmymovie.service.IMovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Value("${file.upload.location}")
	private String location;
	@Autowired
	IMovieService movieService ;
	
	
	@PostMapping()
	public ResponseEntity<?> createMovie(@RequestBody Movie movie ) {
		movie = movieService.createMovie(movie);
		if(movie == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(movie, HttpStatus.CREATED);
	}

	@PostMapping("/upload/{id}")
	public ResponseDTO fileUploadWithParams(@PathVariable int id, @RequestParam MultipartFile imageFile) {
		movieService.addIcon(id, imageFile);
		try {
		imageFile.transferTo(new File(location, imageFile.getOriginalFilename()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseDTO("File uploaded :" + imageFile.getOriginalFilename() + " @ ", LocalDateTime.now());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
		movie = movieService.updateMovie(id,movie);
		if(movie == null)
			return new ResponseEntity<>(movie, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(movie,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllMovie() {
		List<Movie> movies = movieService.getAllMovie();
		if (movies.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAllMovieById(@PathVariable int id){
		Optional<Movie> movies = movieService.getAllMovieById(id);
		if (movies.isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(movies, HttpStatus.OK);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable int id) {
		movieService.deleteMovie(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
