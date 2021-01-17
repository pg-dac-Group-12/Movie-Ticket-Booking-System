package com.app.bookmymovie.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmymovie.pojo.Transaction;
import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.repository.TransactionRepository;

@Service
@Transactional
public class PaymentService implements IPaymentService {

	@Autowired
	TransactionRepository transactionRepo ;
	
	@Override
	public boolean refundPayment(Transaction transaction) {
		if(transaction == null)
			return false ;
		//TODO Payment Gateway Code 
		System.out.println("Refund has been Initiated");
		return true;
	}

	@Override
	public Transaction initiatePayment(double amount, User user) {
		
		//TODO Payment Gateway
		return new Transaction();
	}

}
