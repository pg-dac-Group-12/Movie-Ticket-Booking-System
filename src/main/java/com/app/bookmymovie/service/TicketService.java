package com.app.bookmymovie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.repository.TicketRepository;

public class TicketService implements ITicketService{
	@Autowired
	TicketRepository ticketRepo ;
	
	@Override
	public Optional<Ticket> getTicketById(int id) {
		return ticketRepo.findById(id);
	}

	@Override
	public Optional<Ticket> getAllTicketsByUserId(int userId) {
		return ticketRepo.findAllTicketByUserId(userId);
	}

}
