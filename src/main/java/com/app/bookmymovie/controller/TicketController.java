package com.app.bookmymovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	TicketService ticketService ;
	@GetMapping
	public ResponseEntity<?> getAllTicketsByUserId(@RequestParam int userId) {
		if(!ticketService.getAllTicketsByUserId(userId).isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(ticketService.getAllTicketsByUserId(userId).get(), HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<?> getMovieById(@PathVariable int id) {
		if(!ticketService.getTicketById(id).isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(ticketService.getTicketById(id).get(), HttpStatus.OK);
	}
}
