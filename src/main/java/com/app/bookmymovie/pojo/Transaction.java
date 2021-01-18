package com.app.bookmymovie.pojo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "trasaction_tbl")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String transactionId ;
	private double amount;
	private LocalDateTime time; 
	@OneToOne(mappedBy = "transaction")
	@JsonIgnoreProperties("transaction")
	private Ticket ticket;
	PaymentMode paymentMode;
	
	
	public Transaction( double amount, LocalDateTime time, Ticket ticket, PaymentMode paymentMode , String transactionId) {
		super();
		this.amount = amount;
		this.time = time;
		this.ticket = ticket;
		this.paymentMode = paymentMode;
		this.transactionId = transactionId;
	}


	public Transaction() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public LocalDateTime getTime() {
		return time;
	}


	public void setTime(LocalDateTime time) {
		this.time = time;
	}


	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	public PaymentMode getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	//helper methods for bi-directional association
	public void addTicket(Ticket ticket) {
		this.setTicket(ticket);
		ticket.setTransaction(this);
	}
	
	public void removeTicket(Ticket ticket) {
		this.setTicket(null);
		ticket.setTransaction(null);
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", time=" + time + ", ticket=" + ticket
				+ ", paymentMode=" + paymentMode + "]";
	}


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}
