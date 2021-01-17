package com.app.bookmymovie.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.bookmymovie.pojo.Shows;
import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.pojo.Transaction;
import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.repository.ShowsRepository;
import com.app.bookmymovie.repository.TicketRepository;

@Service
@Transactional
public class TicketService implements ITicketService{
	@Autowired
	TicketRepository ticketRepo ;
	
	@Autowired
	ShowsRepository showRepo ;
	@Autowired
	IPaymentService paymentService;
	
	@Override
	public Optional<Ticket> getTicketById(int id) {
		return ticketRepo.findById(id);
	}

	@Override
	public Optional<Ticket> getAllTicketsByUserId(int userId) {
		return ticketRepo.findAllTicketByUserId(userId);
	}

	@Override
	public boolean cancelTicket(Ticket ticket) {
		LocalDate ticketDate = ticket.getDate() ;
		LocalTime ticketTime = ticket.getTime();
		if(ticketDate.equals(LocalDate.now()) && ticketTime.plusHours(4).isAfter(LocalTime.now())) 
			return false;
		ticketRepo.deleteById(5);
		return paymentService.refundPayment(ticket.getTransaction()); 
	}

	@Override
	public Ticket createTicket(int showId , Integer[] seats, User user ) {
		Ticket ticket = new Ticket() ;
		Shows show = showRepo.findById(showId).get();
		ticket.addShow(show);
		double amount = seats.length * show.getPrice();
		ticket.setAmount(amount);
		user.addTicket(ticket);
		/* Transaction transaction = */ paymentService.initiatePayment( amount , user);
		//ticket.setTransaction(transaction);
		ticket.setTime(show.getTime());
		ticket.setDate(show.getDate());
		ticket.setSeats(seats);
		return ticketRepo.save(ticket) ;
	}


}
