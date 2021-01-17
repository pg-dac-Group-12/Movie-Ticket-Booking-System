package com.app.bookmymovie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bookmymovie.pojo.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer>{
	Optional<Ticket> findAllTicketByUserId(int userId);
}