package com.app.bookmymovie.service;

import java.util.List;

import com.app.bookmymovie.pojo.Audi;
import com.app.bookmymovie.pojo.Theatre;

public interface ITheatreService {
	
	Theatre getTheatre(int id);
	
	Theatre createTheatre(Theatre theatre);
	
	Theatre updateTheatre(int id, Theatre theatre);
	
	void deleteTheatre(int id);
	
	List<Audi> getAudis(int theatreID);
	
	Audi getAudi(int theatreID, int audiId);
	
	Audi createAudi(int theatreID, Audi audi);
	
	Audi updateAudi(int theatreID, int audiID, Audi audi);
	
	void deleteAudi(int theatreID, int audiID);
}
