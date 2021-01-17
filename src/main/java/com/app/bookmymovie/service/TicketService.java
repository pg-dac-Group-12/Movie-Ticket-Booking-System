package com.app.bookmymovie.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.repository.TicketRepository;

public class TicketService implements ITicketService{
	@Autowired
	TicketRepository ticketRepo ;
	
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
		ticketRepo.delete(ticket);
		return paymentService.refundPayment(ticket.getTransaction());
	}

}
