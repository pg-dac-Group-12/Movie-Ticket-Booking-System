package com.app.bookmymovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.bookmymovie.dto.RazorpayDTO;
import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.service.IPaymentService;
import com.razorpay.Order;
@RestController
@RequestMapping("/payment")
@CrossOrigin("http://localhost:4200")
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
	
	@PostMapping("/success")
	public ResponseEntity<?> paymentSuccess(@RequestBody RazorpayDTO razorpayDTO , @RequestParam int tempTicketId , @AuthenticationPrincipal String user ) {
		Ticket ticket = paymentService.paymentSuccess(razorpayDTO, tempTicketId, user);
		if(ticket == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
}
