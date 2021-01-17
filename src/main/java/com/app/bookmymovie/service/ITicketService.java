package com.app.bookmymovie.service;

import java.util.Optional;

import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.pojo.User;

public interface ITicketService {
	Optional<Ticket> getTicketById(int id);
	Optional<Ticket> getAllTicketsByUserId(int userId);
	boolean cancelTicket(Ticket ticket);
	Ticket createTicket(int showId, int[] seats, User user);
}
