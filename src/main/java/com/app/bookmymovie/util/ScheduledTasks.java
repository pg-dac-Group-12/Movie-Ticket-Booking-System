package com.app.bookmymovie.util;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.app.bookmymovie.service.IMovieService;
import com.app.bookmymovie.service.IShowService;
@Configuration
public class ScheduledTasks {
	@Autowired
	IShowService showService ;
	
	@Autowired
	IMovieService movieService;
	
	@Scheduled( cron = "${deleteShows.cron}")
	public void deleteDoneShows() {
		showService.deleteDoneShows();
	}
	
	@Scheduled(cron = "${deleteMovies.cron}")
	public void deleteUnscreenedMovies() {
		movieService.deleteUnscreenedMovies();
	}
}
