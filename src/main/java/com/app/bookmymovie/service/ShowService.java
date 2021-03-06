package com.app.bookmymovie.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmymovie.pojo.Audi;
import com.app.bookmymovie.pojo.Movie;
import com.app.bookmymovie.pojo.Seat;
import com.app.bookmymovie.pojo.Shows;
import com.app.bookmymovie.pojo.Theatre;
import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.repository.AudiRepository;
import com.app.bookmymovie.repository.ShowsRepository;

@Service
@Transactional
public class ShowService implements IShowService {

	@Autowired
	ShowsRepository showsRepo;

	@Autowired
	AudiRepository audiRepo;

	@Autowired
	TicketService ticketService;

	@Override
	public List<Shows> getAllShowsByMovieIdAndDate(int id, LocalDate date) {

		List<Shows> shows = showsRepo.findAllByMovieIdAndDate(id, date);
		return shows;
	}

	@Override
	public Optional<Shows> getShowById(int id) {
		return showsRepo.findById(id);
	}

	@Override
	public Shows createShow(Shows show, int theatreID, int audiID, int movieID) {
		Optional<Audi> audiOptional = audiRepo.findById(audiID);

		if (!audiOptional.isPresent()) {
			System.out.println("Audi not found"); // for debuging purpose
		}
		Audi audi = audiOptional.get();
		Theatre theatre = new Theatre();
		theatre.setId(theatreID);
		Movie movie = new Movie();
		movie.setId(movieID);
		show.setAudi(audi);
		System.out.println(" ShowService.createShow-" + audi.getSeatMap());
		show.setSeatmap(audi.getSeatMap());
		System.out.println(" ShowService.createShow-" + show.getSeatmap());
		show.setTheatre(theatre);
		show.setMovie(movie);
		return showsRepo.save(show);
	}

	@Override
	public Shows updateShow(Shows show, int id) {
		show.setId(id);
		return showsRepo.save(show);
	}

	@Override
	public void deleteDoneShows() {
		showsRepo.deleteByDateBefore(LocalDate.now());
	}

	@Override
	public List<Shows> getAllShowsByTheatreId(int theatreId) {

		return showsRepo.findAllByTheatreId(theatreId);
	}

	@Override
	public List<Shows> getAllShowsByAudiId(int audiId) {
		return showsRepo.findAllShowsByAudiId(audiId);
//		if(!showsListOptional.isPresent())
//			return null;
//		return showsListOptional.get();
	}

	@Override
	public boolean cancelShow(int showId) {
		Optional<Shows> optionalShow = showsRepo.findById(showId);
		if (optionalShow.isPresent()) {
			for (Ticket ticket : optionalShow.get().getTickets()) {
				if (!ticketService.cancelTicket(ticket.getId()))
					return false;
			}
			showsRepo.delete(optionalShow.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Seat> getSeatMap(int id) {
		Optional<Shows> show = showsRepo.findById(id);
		System.out.println("ShowService.getSeatMap-" + show.get().getSeatmap());
		return (show.get().getSeatmap());
	}

	@Override
	public void updateSeatMap(int showId, List<Seat> bookedSeats, boolean status) {
		List<Seat> seats = this.getShowById(showId).get().getSeatmap();
		System.out.println("updateSeatMap-1" + seats);
		for (Seat bookedSeat : bookedSeats) {
			for (int i = 0; i < seats.size(); i++) {
				if ( i == (seats.size()-1) ? false : seats.get(i).getRowNumber() == seats.get(i + 1).getRowNumber()) {
					continue;
				}
				if (seats.get(i).getRowNumber() == bookedSeat.getRowNumber()
						&& seats.get(i).getColNumber() == bookedSeat.getColNumber()) {
					seats.get(i).setIsBooked(status);
				}
			}
		}
		System.out.println("updateSeatMap-2" + seats);
	}
}
