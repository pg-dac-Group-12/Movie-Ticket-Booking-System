package com.app.bookmymovie.service;

import com.app.bookmymovie.dto.RazorpayDTO;
import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.pojo.Transaction;
import com.razorpay.Order;

public interface IPaymentService {
	boolean refundPayment(Transaction transaction);
	Order createOrder(double amount);
	Transaction createTransaction(String razorPaymentId, Ticket ticket);
	Ticket paymentSuccess(RazorpayDTO razorpayDTO, int tempTicketId, String userName);
	
}