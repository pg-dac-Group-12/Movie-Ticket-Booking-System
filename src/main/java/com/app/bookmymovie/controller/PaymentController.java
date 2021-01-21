package com.app.bookmymovie.controller;

import static com.app.bookmymovie.util.EmailUtil.sendTicketViaEmail;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.dto.RazorpayDTO;
import com.app.bookmymovie.pojo.Audi;
import com.app.bookmymovie.pojo.Movie;
import com.app.bookmymovie.pojo.Seat;
import com.app.bookmymovie.pojo.Shows;
import com.app.bookmymovie.pojo.Theatre;
import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.pojo.Transaction;
import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.service.IPaymentService;
import com.razorpay.Order;

import io.jsonwebtoken.lang.Collections;
@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired 
	IPaymentService paymentService ;
	
	@GetMapping("/order_id")
	public ResponseEntity<?> createOrder(@RequestParam double amount) {
		Order orderId = paymentService.createOrder(amount);
		if(orderId == null) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(orderId.toString() ,HttpStatus.OK);
	}
	
	@GetMapping("/success")
	public ResponseEntity<?> paymentSuccess(@RequestBody RazorpayDTO razorpayDTO) throws IOException, Exception {
		Ticket ticket = paymentService.paymentSuccess(razorpayDTO);
		/*
		 * Movie movie = new Movie(); movie.setTitle("karz"); Shows show = new Shows(new
		 * Audi(), movie, LocalTime.now(),100d); show.setTheatre(new
		 * Theatre("PVR cinemas","","","","")); Ticket ticket = new Ticket(show, new
		 * User("tanay", "rohanjulka19@gmail.com", "#", "1"),
		 * Collections.arrayToList(new Seat[]{new Seat(2,'h'), new Seat(6,'h')}), 100,
		 * LocalDate.now(), LocalTime.now(), new Transaction());
		 */
		if(ticket == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		sendTicketViaEmail(ticket);
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
}
