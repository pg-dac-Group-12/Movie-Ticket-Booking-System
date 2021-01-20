package com.app.bookmymovie.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bookmymovie.dto.RazorpayDTO;
import com.app.bookmymovie.pojo.Ticket;
import com.app.bookmymovie.pojo.Transaction;
import com.app.bookmymovie.repository.TicketRepository;
import com.app.bookmymovie.repository.TransactionRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@Service
@Transactional
public class PaymentService implements IPaymentService {

	@Autowired
	TransactionRepository transactionRepo ;
	
	@Autowired
	TicketRepository ticketRepo ;
	
	@Autowired
	HttpSession session ;
	
	@Autowired
	TicketService ticketService ;
	
	@Override
	public boolean refundPayment(Transaction transaction) {
		/*
		 * if(transaction == null) return false ;
		 */
		//TODO Payment Gateway Code 
		System.out.println("Refund has been Initiated");
		return true;
	}
	
	@Override
	public Order createOrder(double amount) {
		try {
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_l5ZltcixJREBlt", "pvtBGbaDW5i8HzU3lJpam0s1");
			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", 50000); 
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "order_rcptid_11");
			Order order = razorpayClient.Orders.create(orderRequest);
			return order ;
		} catch (RazorpayException e) {
		  System.out.println(e.getMessage());
		  return null ;
		}
	}
	@Override
	public Ticket paymentSuccess(RazorpayDTO razorpayDTO) {
		JSONObject options = new JSONObject();
		options.put("razorpay_order_id", "<order_id>");
		options.put("razorpay_payment_id", "<payment_id>");
		options.put("razorpay_signature", "<signature>");
		try {
			if(!Utils.verifyPaymentSignature(options, "pvtBGbaDW5i8HzU3lJpam0s1")) {
				ticketService.invalidateTicket();
				session.removeAttribute("ticket");
				return null ;
			}
				
		} catch (RazorpayException e) {
			e.printStackTrace();
		} 
		
		Ticket ticket = (Ticket) session.getAttribute("ticket");
		ticket = ticketRepo.save(ticket);
		System.out.println(ticket.toString());
		Transaction transaction = createTransaction(razorpayDTO.getRazorpayPaymentId(),ticket);
		ticket.setTransaction(transaction);
		transaction = transactionRepo.save(transaction);
		return ticket ;
	}
	
	@Override
	public Transaction createTransaction(String razorPaymentId,Ticket ticket) {
		Transaction transaction = new Transaction() ;
		transaction.addTicket(ticket);
		transaction.setAmount(ticket.getAmount());
		transaction.setTime(LocalDateTime.now());
		transaction.setTransactionId(razorPaymentId);
		
		return transaction ;
	}
}
