package com.app.bookmymovie.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.bookmymovie.pojo.Seat;
import com.app.bookmymovie.pojo.Shows;
import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.pojo.TicketSession;
import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.repository.ShowsRepository;
import com.app.bookmymovie.repository.TicketRepository;
import com.app.bookmymovie.repository.TicketSessionRepository;
import com.app.bookmymovie.repository.UserRepository;

@Service
@Transactional
public class TicketService implements ITicketService{
	@Autowired
	TicketRepository ticketRepo ;
	@Autowired
	TicketSessionRepository ticketSession ;
	@Autowired
	HttpSession session;
	@Autowired
	ShowsRepository showRepo ;
	@Autowired
	ShowService showService ;
	@Autowired
	IPaymentService paymentService;
	@Autowired
	UserRepository userRepo;
	@Override
	public Optional<Ticket> getTicketById(int id) {
		return ticketRepo.findById(id);
	}

	@Override
	public List<Ticket> getAllTicketsByUserId(int userId) {
		
		return ticketRepo.findAllTicketByUserId(userId);
	}

	@Override
	public boolean cancelTicket(int ticketId) {
		Ticket ticket = ticketRepo.findById(ticketId).get() ;
		if(ticket == null ) 
			return false;
		ticketRepo.delete(ticket);
		return paymentService.refundPayment(ticket.getTransaction()); 
	}
	
	@Override
	public Ticket saveTicket(int tempTicketId, String userName) {
		//Ticket ticket =(Ticket) session.getAttribute("Ticket");
		User user = userRepo.findByEmail(userName).get();
		TicketSession tempTicket = ticketSession.findById(tempTicketId).get();
		Ticket ticket = new Ticket(tempTicket.getShow(), user ,tempTicket.getSeats(), tempTicket.getAmount(),LocalDate.now(),LocalTime.now(),null);
		ticketSession.deleteById(tempTicket.getId());
		return ticket;
	}
	
	@Override
	public void invalidateTicket(int tempTicketId) {
		Optional<TicketSession> tempTicketOptional = ticketSession.findById(tempTicketId);
		if(tempTicketOptional.isPresent()) {
			TicketSession tempTicket = tempTicketOptional.get();
			ticketSession.delete(tempTicket);
			showService.updateSeatMap(tempTicket.getShow().getId(), tempTicket.getSeats(), false);
		} 
	}

	@Override
	public TicketSession createTicket(int showId , List<Seat> seats, String userName ) {
		TicketSession tempTicket= new TicketSession();
		Shows show = showRepo.findById(showId).get();
		tempTicket.setShow(show);
		double amount = seats.size() * show.getPrice();
		tempTicket.setAmount(amount);
		tempTicket.setSeats(seats);
		tempTicket.setTimestamp(LocalDateTime.now().plus(15, ChronoUnit.MINUTES));
		ticketSession.save(tempTicket);
		showService.updateSeatMap(showId, seats, true);
		return tempTicket ;
	}

	@Override
	public void deleteTempTickets() {
		List<TicketSession> tempTickets = ticketSession.findAll();
		for(TicketSession tempTicket : tempTickets) {
			if(tempTicket.getTimestamp().isBefore(LocalDateTime.now())) {
				showService.updateSeatMap(tempTicket.getShow().getId(), tempTicket.getSeats(), false);
				ticketSession.deleteById(tempTicket.getId());
			}
		}
	}
}
