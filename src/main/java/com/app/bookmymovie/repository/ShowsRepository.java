package com.app.bookmymovie.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.bookmymovie.pojo.Shows; 
 
public interface ShowsRepository extends JpaRepository<Shows,Integer>{
	@Query("select s from Shows s join fetch s.seatmap where id = :id and date = :date")
	Optional<Shows> findAllByMovieIdAndDate(@Param("id") int id, @Param("date") LocalDate date);
	@Query("select s from Shows s join fetch s.seatmap where id = :id")
	Optional<Shows> findAllByTheatreId(@Param("id") int theatreId);
}
