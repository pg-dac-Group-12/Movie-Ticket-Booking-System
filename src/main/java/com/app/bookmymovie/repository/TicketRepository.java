package com.app.bookmymovie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.app.bookmymovie.pojo.Ticket;
@Transactional
public interface TicketRepository extends JpaRepository<Ticket,Integer>{
	Optional<Ticket> findAllTicketByUserId(int userId);
}
