package com.app.bookmymovie.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.pojo.Theatre;
import com.app.bookmymovie.service.ITheatreService;

@RestController
@RequestMapping("/theatre")
public class TheatreController {
	@Autowired
	ITheatreService theatreService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getTheatre(@PathVariable int id) {
		Theatre theatre = theatreService.getTheatre(id);
		if (theatre == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(theatre, HttpStatus.FOUND);
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
	
}
