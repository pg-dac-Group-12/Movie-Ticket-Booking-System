package com.app.bookmymovie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.bookmymovie.pojo.Theatre;
import com.app.bookmymovie.repository.TheatreRepository;

@Service
@Transactional
public class TheatreService implements ITheatreService {
	
	@Autowired
	private TheatreRepository theatreRepo;
	
	@Override
	public Theatre getTheatre(int id) {
		Optional<Theatre> theatre = theatreRepo.findById(id);
		if(!theatre.isPresent())
			return null;
		return theatre.get();
	}

	@Override
	public Theatre createTheatre(Theatre theatre) {
		Theatre theatreSaved = theatreRepo.save(theatre);
		return theatreSaved;
	}

	@Override
	public Theatre updateTheatre(int id, Theatre theatre) {
		Optional<Theatre> theatreOld = theatreRepo.findById(id);
		if(!theatreOld.isPresent())
			return null;
		theatre.setId(id);
		return theatreRepo.save(theatre);
	}

	@Override
	public void deleteTheatre(int id) {
		theatreRepo.deleteById(id);

	}

}
