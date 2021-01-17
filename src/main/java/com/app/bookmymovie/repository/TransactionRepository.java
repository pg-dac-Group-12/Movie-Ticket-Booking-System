package com.app.bookmymovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bookmymovie.pojo.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
	
}
