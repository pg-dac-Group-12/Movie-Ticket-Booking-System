package com.app.bookmymovie.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.app.bookmymovie.service.IMovieService;
import com.app.bookmymovie.service.IShowService;
import com.app.bookmymovie.service.ITicketService;
@Configuration
public class ScheduledTasks {
	@Autowired
	IShowService showService ;
	
	@Autowired
	IMovieService movieService;
	
	@Autowired
	ITicketService ticketService ;
	
	
	@Scheduled( cron = "${deleteShows.cron}")
	public void deleteDoneShows() {
		showService.deleteDoneShows();
	}
	
	@Scheduled(cron = "${deleteMovies.cron}")
	public void deleteUnscreenedMovies() {
		movieService.deleteUnscreenedMovies();
	}
	
	@Scheduled(fixedRate = 1000)
	public void deleteTempTickets() {
		ticketService.deleteTempTickets();
	}
	
}
