package com.app.bookmymovie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.pojo.Audi;
import com.app.bookmymovie.pojo.Theatre;
import com.app.bookmymovie.service.ITheatreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/theatre")
@CrossOrigin(origins= "http://localhost:4200")
public class TheatreController {
	@Autowired
	ITheatreService theatreService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getTheatre(@PathVariable int id) {
		Theatre theatre = theatreService.getTheatre(id);
		if (theatre == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(theatre, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createTheatre(@RequestBody Theatre theatre) {
		theatre = theatreService.createTheatre(theatre);
		if (theatre == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(theatre, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTheatre(@PathVariable int id, @RequestBody Theatre theatre) {
		theatre = theatreService.updateTheatre(id,theatre);
		if (theatre == null)
			return new ResponseEntity<>(theatre, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(theatre, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTheatre(@PathVariable int id) {
		theatreService.deleteTheatre(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{theatreID}/audis")
	public ResponseEntity<?> getAllAudis(@PathVariable int theatreID){
		List<Audi> audis = theatreService.getAudis(theatreID);
		if(audis == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(audis, HttpStatus.OK);
	}
	
	@GetMapping("/{theatreID}/audi/{audiID}")
	public ResponseEntity<?> getAudi(@PathVariable int theatreID, @PathVariable int audiID){
		Audi audi = theatreService.getAudi(theatreID, audiID);
		if(audi == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(audi, HttpStatus.OK);
	}
	
	@PostMapping("/{theatreID}/audi")
	public ResponseEntity<?> createAudi(@PathVariable int theatreID, @RequestBody Audi audi) throws JsonMappingException, JsonProcessingException{
		System.out.println("TheatreController.crateAUdi-"+audi.getSeatMap());
		Audi createdAudi = theatreService.createAudi(theatreID, audi);
		if(createdAudi == null)
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<>(createdAudi, HttpStatus.CREATED);
	}
	
	@PutMapping("{theatreID}/audi/{audiID}")
	public ResponseEntity<?> updateAudi(@PathVariable int theatreID, @PathVariable int audiID, @RequestBody Audi audi){
		Audi updatedAudi = theatreService.updateAudi(theatreID, audiID, audi);
		if(updatedAudi == null)
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<>(updatedAudi, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{theatreID}/audi/{audiID}")
	public ResponseEntity<?> deleteAudi(@PathVariable int theatreID, @PathVariable int audiID){
		theatreService.deleteAudi(theatreID, audiID);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
