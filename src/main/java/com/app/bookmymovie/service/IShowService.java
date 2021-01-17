package com.app.bookmymovie.service;

import java.time.LocalDate;
import java.util.Optional;

import com.app.bookmymovie.pojo.Shows;

public interface IShowService {
	Optional<Shows> getAllShowsByMovieIdAndDate(int id, LocalDate date);
	Optional<Shows> getShowById(int id); // TODO this should return seatmap
	Optional<Shows> getAllShowsByTheatreId(int theatreId);
	Shows createShow(Shows show);
	Shows updateShow(Shows show, int id);
	void deleteShow(int id);
	//TODO updateSeatmap
}
