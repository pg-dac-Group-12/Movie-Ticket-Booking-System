package com.app.bookmymovie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.bookmymovie.pojo.Audi;
import com.app.bookmymovie.pojo.Theatre;
import com.app.bookmymovie.repository.AudiRepository;
import com.app.bookmymovie.repository.TheatreRepository;

@Service
@Transactional
public class TheatreService implements ITheatreService {

	@Autowired
	private TheatreRepository theatreRepo;

	@Autowired
	private AudiRepository audiRepo;
	
	@Override
	public Theatre getTheatre(int id) {
		Optional<Theatre> theatre = theatreRepo.findById(id);
		if (!theatre.isPresent())
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
		if (!theatreOld.isPresent())
			return null;
		theatre.setId(id);
		return theatreRepo.save(theatre);
	}

	@Override
	public void deleteTheatre(int id) {
		theatreRepo.deleteById(id);
	}

	@Override
	public List<Audi> getAudis(int theatreID) {
		Optional<Theatre> theatre = theatreRepo.findById(theatreID);
		if (!theatre.isPresent())
			return null;
		return theatre.get().getAudis();
	}

	@Override
	public Audi getAudi(int theatreID, int audiNumber) {
		Optional<Theatre> theatre = theatreRepo.findById(theatreID);
		if (!theatre.isPresent())
			return null;
		for (Audi a : theatre.get().getAudis())
			if (audiNumber == a.getNumber())
				return a;
		return null;
	}

	@Override
	public Audi createAudi(int theatreID, Audi audi) {
		Optional<Theatre> theatre = theatreRepo.findById(theatreID);
		if (!theatre.isPresent())
			return null;
		theatre.get().addAudi(audi);
		audi = audiRepo.save(audi);
		return audi;
	}

	@Override
	public Audi updateAudi(int theatreID, int audiId, Audi audi) {
		Optional<Theatre> theatre = theatreRepo.findById(theatreID);
		audi.setId(audiId);
		audi.setTheatre(theatre.get());
		audiRepo.save(audi);
		return audi;
	}

	@Override
	public void deleteAudi(int theatreID, int audiID) {
		audiRepo.delete(audiRepo.findById(audiID).get());
	}
}
