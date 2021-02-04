package com.app.bookmymovie.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.app.bookmymovie.dto.ImageDTO;
import com.app.bookmymovie.dto.ResponseDTO;
import com.app.bookmymovie.pojo.Movie;
import com.app.bookmymovie.service.IMovieService;

@RestController
@RequestMapping("/movie")
@CrossOrigin("http://localhost:4200")
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
	public ResponseDTO fileUpload(@PathVariable int id, @RequestParam List<MultipartFile> imageFile) {
		int name = 1 ;
		for (MultipartFile m : imageFile) {
			double size = m.getSize();
	        double megabytes = (size / (1024*1024));
	        if(megabytes > 5) {
	        	return new ResponseDTO("File not  uploaded :" + m.getOriginalFilename() + " @ ", LocalDateTime.now());				
	        }
	        else {
	        	movieService.addIcon(id, m, name);
	        	name = name + 1 ;
	        }     
		 	}
		 	return new ResponseDTO("File uploaded :" + " @ ", LocalDateTime.now());
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<?> getAllIconById(@PathVariable int id) throws IOException{

		int fileName = 1;
		//Path path = Paths.get("/home/jatin/Desktop/1/" +fileName +"/");
		//ImageDTO img = new ImageDTO();
		List<ImageDTO> img = new ArrayList<ImageDTO>();
		List<Movie> iconContentType = movieService.getIconContentType(id);
		try{
			//for(;fileName < 100;fileName++ )
			for(Movie m : iconContentType){
				for(String s : m.getIconContentType()) {
				Path path = Paths.get("/home/jatin/Desktop/1/" +fileName +"/");
				//String ext1 = FilenameUtils.getExtension("/home/jatin/Desktop/1/1.jpg"); 
				//System.out.println(ext1);
					img.add(new ImageDTO(Files.readAllBytes(path), s));
					fileName++ ;
			}
			}
		}
		catch(Exception e) {
			if(fileName == 1)
			return new ResponseEntity<>("no image" , HttpStatus.OK);
		}
			
		return new ResponseEntity<>(img , HttpStatus.OK);
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
		if (!movies.isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(movies, HttpStatus.OK);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable int id) {
		movieService.deleteMovie(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
