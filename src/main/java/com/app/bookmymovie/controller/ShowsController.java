package com.app.bookmymovie.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.app.bookmymovie.pojo.Shows;
import com.app.bookmymovie.service.IShowService;

@RestController
@RequestMapping("/shows")
public class ShowsController {
	@Autowired
	IShowService showService ;
	
	@GetMapping
	public ResponseEntity<?> getAllShowsByMovieIdAndDate(@RequestParam int movieId, @RequestParam LocalDate date) {
		if(!showService.getAllShowsByMovieIdAndDate(movieId,date).isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(showService.getAllShowsByMovieIdAndDate(movieId,date).get(), HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<?> getShowById(@PathVariable int id) {
		if(!showService.getShowById(id).isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(showService.getShowById(id).get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createShow(@RequestBody Shows show) {
		show = showService.createShow(show);
		if(show == null ) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(show,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateShow(@RequestBody Shows show, @PathVariable int id) {
		show = showService.updateShow(show, id);
		if(show == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(show,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteShow(@PathVariable int id) {
		showService.deleteShow(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
