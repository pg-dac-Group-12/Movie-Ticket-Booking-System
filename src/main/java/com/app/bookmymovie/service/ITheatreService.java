package com.app.bookmymovie.service;

import com.app.bookmymovie.pojo.Theatre;

public interface ITheatreService {
	
	Theatre getTheatre(int id);
	
	Theatre createTheatre(Theatre theatre);
	
	Theatre updateTheatre(int id, Theatre theatre);
	
	void deleteTheatre(int id);
	
}
