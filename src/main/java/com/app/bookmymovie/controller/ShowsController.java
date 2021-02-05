package com.app.bookmymovie.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.app.bookmymovie.pojo.Seat;
import com.app.bookmymovie.pojo.Shows;
import com.app.bookmymovie.service.IShowService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/shows")
@CrossOrigin("http://localhost:4200")
public class ShowsController {
	@Autowired
	IShowService showService ;
	
	@GetMapping
	public ResponseEntity<?> getAllShowsByMovieIdAndDate(@RequestParam int movieId, @RequestParam("date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		
		if(showService.getAllShowsByMovieIdAndDate(movieId,date) == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(showService.getAllShowsByMovieIdAndDate(movieId,date), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getShowById(@PathVariable int id) {
		if(!showService.getShowById(id).isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(showService.getShowById(id).get(), HttpStatus.OK);
	}
	
	@GetMapping("/theatre/{theatreID}")
	public ResponseEntity<?> getAllShowsByTheatreId(@PathVariable int theatreID) {
		if(showService.getAllShowsByTheatreId(theatreID).isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(showService.getAllShowsByTheatreId(theatreID), HttpStatus.OK);
	}
	
	@GetMapping("/audi/{audiId}")
	public ResponseEntity<?> getAllShowsByAudiId(@PathVariable int audiId) {
		if(!showService.getAllShowsByAudiId(audiId).isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(showService.getAllShowsByAudiId(audiId).get(), HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<?> createShow(@RequestBody Shows show,@RequestParam int theatreID, @RequestParam int audiID, @RequestParam int movieID) throws JsonMappingException, JsonProcessingException {
		show = showService.createShow(show, theatreID, audiID, movieID);
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
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> deleteShow(@PathVariable int id) {
//		showService.deleteDoneShowa();
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
//	
	@DeleteMapping("/cancel/{showId}")
	public ResponseEntity<?> cancelShow(@PathVariable int showId) {
		if(!showService.cancelShow(showId))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{showId}/seatmap")
	public ResponseEntity<?> getSeatMap(@PathVariable int showId){
		System.out.println(showService.getSeatMap(showId));
		if(showService.getSeatMap(showId).isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<> (showService.getSeatMap(showId), HttpStatus.OK);
		
	}
	
	@PutMapping("/{showId}/seatmap")
	public ResponseEntity<?> updateSeatMap(@PathVariable int showId, @RequestBody List<Seat> seat){
		if(!showService.updateSeatMap(showId, seat, true))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<> (showService.getSeatMap(showId),HttpStatus.OK);
		
	}
	
}
