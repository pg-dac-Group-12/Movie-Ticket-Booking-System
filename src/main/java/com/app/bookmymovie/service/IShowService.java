package com.app.bookmymovie.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.app.bookmymovie.pojo.Seat;
import com.app.bookmymovie.pojo.Shows;

public interface IShowService {
	List<Shows> getAllShowsByMovieIdAndDate(int id, LocalDate date);
	Optional<Shows> getShowById(int id); // TODO this should return seatmap
	List<Shows> getAllShowsByTheatreId(int theatreId);
	List<Shows> getAllShowsByAudiId(int AudiId);
	Shows createShow(Shows show,int theatreID, int audiID,  int movieID);
	Shows updateShow(Shows show, int id);
	void deleteDoneShows();
	boolean cancelShow(int showId);
	List<Seat> getSeatMap(int id);
	void updateSeatMap(int showId, List<Seat> seat, boolean status);
	
	//TODO updateSeatmap
}
