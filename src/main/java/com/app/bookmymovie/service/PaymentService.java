package com.app.bookmymovie.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmymovie.pojo.Transaction;
import com.app.bookmymovie.repository.TransactionRepository;

@Service
@Transactional
public class PaymentService implements IPaymentService {

	@Autowired
	TransactionRepository transactionRepo ;
	
	@Override
	public boolean refundPayment(int transactionId) {
		Optional<Transaction> transaction = transactionRepo.findById(transactionId);
		if(!transaction.isPresent())
			return false ;
		//TODO Payment Gateway Code
		System.out.println("Refund has been Initiated");
		return false;
	}

}
