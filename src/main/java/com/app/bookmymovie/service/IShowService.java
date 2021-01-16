package com.app.bookmymovie.service;

import java.util.Optional;

import com.app.bookmymovie.pojo.Shows;

public interface IShowService {
	Optional<Shows> getAllShowsByMovieId(int id);
	Optional<Shows> getShowById(int id);
	Shows createShow(Shows show);
	Shows updateShow(Shows show, int id);
	void deleteShow(int id);
	//TODO updateSeatmap
}
