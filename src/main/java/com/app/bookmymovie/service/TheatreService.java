package com.app.bookmymovie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.bookmymovie.pojo.Audi;
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
	public Audi getAudi(int theatreID, int audiId) {
		Optional<Theatre> theatre = theatreRepo.findById(theatreID);
		if (!theatre.isPresent())
			return null;
		for (Audi a : theatre.get().getAudis())
			if (audiId == a.getId())
				return a;
		return null;
	}

	@Override
	public Audi createAudi(int theatreID, Audi audi) {
		Optional<Theatre> theatre = theatreRepo.findById(theatreID);
		if (!theatre.isPresent())
			return null;
		boolean isAdded = theatre.get().addAudi(audi);
		if (theatreRepo.save(theatre.get()) != null && isAdded)
			return audi;
		return null;
	}

	@Override
	public Audi updateAudi(int theatreID, int audiID, Audi audi) {
		Optional<Theatre> theatre = theatreRepo.findById(theatreID);
		if (!theatre.isPresent())
			return null;
		for (Audi a : theatre.get().getAudis()) {
			if (a.getId() == audiID) {
				theatre.get().removeAudi(a);
				if (theatre.get().addAudi(audi))
					return audi;
			}
		}
		return null;
	}

	@Override
	public void deleteAudi(int theatreID, int audiID) {
		Optional<Theatre> theatre = theatreRepo.findById(theatreID);
		if (theatre.isPresent()) {
			for (Audi audi : theatre.get().getAudis())
				theatre.get().removeAudi(audi);
		}
	}
}
