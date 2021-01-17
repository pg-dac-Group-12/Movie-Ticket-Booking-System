package com.app.bookmymovie.service;

import com.app.bookmymovie.pojo.Transaction;

public interface IPaymentService {
	boolean refundPayment(Transaction transaction);
	
}