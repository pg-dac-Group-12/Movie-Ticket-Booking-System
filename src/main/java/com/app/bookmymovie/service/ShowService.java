package com.app.bookmymovie.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmymovie.pojo.Shows;
import com.app.bookmymovie.repository.ShowsRepository;

@Service
@Transactional
public class ShowService implements IShowService {
	
	@Autowired
	ShowsRepository showsRepo ;
	
	@Override
	public Optional<Shows> getAllShowsByMovieIdAndDate(int id, LocalDate date) {
		return showsRepo.findAllByMovieIdAndDate(id, date);
	}

	@Override
	public Optional<Shows> getShowById(int id) {
		return showsRepo.findById(id);
	}

	@Override
	public Shows createShow(Shows show) {
		 return showsRepo.save(show);
	}

	@Override
	public Shows updateShow(Shows show , int id) {
		show.setId(id);
		return showsRepo.save(show);
	}

	@Override
	public void deleteShow(int id) {
		showsRepo.deleteById(id);
	}

}
