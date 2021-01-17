package com.app.bookmymovie.service;

import com.app.bookmymovie.pojo.Transaction;
import com.app.bookmymovie.pojo.User;

public interface IPaymentService {
	boolean refundPayment(Transaction transaction);

	Transaction initiatePayment(double amount, User user);
	
}