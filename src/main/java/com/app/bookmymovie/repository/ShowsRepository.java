package com.app.bookmymovie.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.app.bookmymovie.pojo.Shows; 
 
public interface ShowsRepository extends JpaRepository<Shows,Integer>{

	
	List<Shows> findAllByMovieIdAndDate(int id, LocalDate date);
	
	List<Shows> findAllByTheatreId( int theatreId);
	List<Shows> findAllShowsByAudiId(int audiId);

	
	@Transactional
	@Modifying
	@Query("delete from Shows s where s.date < :now")
	void deleteByDateBefore(@Param("now")LocalDate now);
}
