package com.app.bookmymovie.pojo;

import java.time.LocalDateTime;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ticket_session_tbl")
public class TicketSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "show_id")
	@JsonIgnoreProperties("tickets")
	private Shows show;
	@Embedded
	@ElementCollection(fetch=FetchType.EAGER)
	private List<Seat> seats ;
	private double amount;
	private LocalDateTime timestamp ;
	public TicketSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TicketSession(Integer id, Shows show, User user, List<Seat> seats, double amount, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.show = show;
		this.seats = seats;
		this.amount = amount;
		this.timestamp = timestamp;
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
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "TicketSession [id=" + id + ", show=" + show + ", user=" + ", seats=" + seats + ", amount="
				+ amount + ", timestamp=" + timestamp + "]";
	}
	
}
