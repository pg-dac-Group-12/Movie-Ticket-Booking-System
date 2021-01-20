package com.app.bookmymovie.pojo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ticket_tbl")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "show_id")
	@JsonIgnoreProperties("tickets")
	private Shows show;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userID")
	@JsonIgnoreProperties("tickes")
	private User user;
	@Embedded
	@ElementCollection(fetch=FetchType.LAZY)
	private List<Seat> seats ;
	private double amount;
	private LocalTime time ;
	private LocalDate date ;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="transaction_id")
	@JsonIgnoreProperties("ticket")
	private Transaction transaction;

	public Ticket() {};
	public Ticket( Shows show, User user, List<Seat> seats, double amount,LocalDate date , LocalTime time , Transaction transaction) {
		super();
		this.show = show;
		this.user = user;
		this.seats = seats;
		this.amount = amount;
		this.date = date ;
		this.time = time ;
		this.transaction = transaction;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Shows getShow() {
		return show;
	}

	
	public void setShow(Shows show) {
		this.show = show;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Seat> getSeats() {
		return seats;
	}


	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Transaction getTransaction() {
		return transaction;
	}


	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public void addShow(Shows show) {
		this.show = show ;
		show.addTicket(this);
		
	}	

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", show=" + show + ", user=" + user	+ ", amount=" + amount + ", transaction=" + transaction + "]";
	}
}
