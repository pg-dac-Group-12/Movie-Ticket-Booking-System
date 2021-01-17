package com.app.bookmymovie.pojo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "shows_tbl")
public class Shows {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "audi_id")
	private Audi audi;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id")
	private Movie movie;
	@ManyToOne
	@JoinColumn(name = "theatre_id")
	private Theatre theatre;
	@OneToMany(mappedBy = "show")
	@JsonIgnoreProperties("show")
	private List<Ticket> tickets = new ArrayList<>() ;
	private LocalTime time;
	private LocalDate date ;
	private double price;
	private byte[] seatMap;
	
	public Shows() {};
	public Shows(Audi audi, Movie movie, LocalTime time, double price, byte[] seatMap , LocalDate date) {
		super();
		this.audi = audi;
		this.movie = movie;
		this.time = time;
		this.price = price;
		this.seatMap = seatMap;
		this.date = date ;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public Audi getAudi() {
		return audi;
	}
	public void setAudi(Audi audi) {
		this.audi = audi;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public byte[] getSeatMap() {
		return seatMap;
	}
	public void setSeatMap(byte[] seatMap) {
		this.seatMap = seatMap;
	}
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	@Override
	public String toString() {
		return "Shows [id=" + id + ", audi=" + audi + ", movie=" + movie + ", time=" + time + ", price=" + price
				+ ", seatMap=" + Arrays.toString(seatMap) + "]";
	}
}
