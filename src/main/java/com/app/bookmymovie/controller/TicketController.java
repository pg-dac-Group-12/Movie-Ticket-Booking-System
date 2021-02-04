package com.app.bookmymovie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.pojo.Seat;
import com.app.bookmymovie.pojo.TicketSession;
import com.app.bookmymovie.service.TicketService;

@RestController
@RequestMapping("/ticket")
@CrossOrigin("http://localhost:4200")
public class TicketController {
	@Autowired
	TicketService ticketService ; 
	@GetMapping
	public ResponseEntity<?> getAllTicketsByUserId(@RequestParam int userId) {
		if(ticketService.getAllTicketsByUserId(userId).isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(ticketService.getAllTicketsByUserId(userId), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getTicketById(@PathVariable int id) {
		if(!ticketService.getTicketById(id).isPresent())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(ticketService.getTicketById(id).get(), HttpStatus.OK);
	}
	
	@PostMapping("/cancel/{ticketId}")
	public ResponseEntity<?> cancelTicket(@PathVariable int ticketId) {
		if(!ticketService.cancelTicket(ticketId))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/{showId}")
	public ResponseEntity<?> createTicket(@RequestBody List<Seat> seats , @PathVariable int showId, @AuthenticationPrincipal String user) {
		TicketSession tempTicket= ticketService.createTicket(showId, seats, user) ;
		if(tempTicket == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(tempTicket ,HttpStatus.OK);
	}
	
	@GetMapping("/invalidate/{ticketid}")
	public ResponseEntity<?> invalidateTicket(@PathVariable int ticketid){
		ticketService.invalidateTicket(ticketid);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
