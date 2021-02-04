package com.app.bookmymovie.service;

import java.util.List;
import java.util.Optional;

import com.app.bookmymovie.pojo.Seat;
import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.pojo.TicketSession;
import com.app.bookmymovie.pojo.User;

public interface ITicketService {
	Optional<Ticket> getTicketById(int id);
	List<Ticket> getAllTicketsByUserId(int userId);
	boolean cancelTicket(int ticketId);
	TicketSession createTicket(int showId, List<Seat> seats, String user);
	void deleteTempTickets();
	Ticket saveTicket(int tempTicketId, String userName);
	void invalidateTicket(int tempTicketId);
}
