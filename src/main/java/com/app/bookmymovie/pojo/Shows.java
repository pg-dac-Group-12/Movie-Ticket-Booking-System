package com.app.bookmymovie.pojo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

@Entity
@Table(name = "shows_tbl")
public class Shows {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "audi_id")
	private Audi audi;
	@ManyToOne()
	@JoinColumn(name = "movie_id")
	private Movie movie;
	@ManyToOne()
	@JoinColumn(name = "theatre_id")
	private Theatre theatre;
	@OneToMany(mappedBy = "show")
	@JsonIgnoreProperties("show")
	private List<Ticket> tickets = new ArrayList<>() ;
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime time;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate date ;
	
	private double price;
	@Embedded
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "show_seats_tbl" , joinColumns = @JoinColumn(name = "show_seats_id"))

	private List<Seat> seatmap ;
	public Shows() {
		super();
	}
	public List<Seat> getSeatmap() {
		return seatmap;
	}
	public void setSeatmap(List<Seat> seatmap) {
		this.seatmap = seatmap;
	}

	public Shows(Audi audi ,Movie movie, LocalTime time, double price ) {
		super();
		this.audi = audi ;
		this.seatmap = audi.getSeatMap();
		this.movie = movie;
		this.time = time;
		this.price = price;
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
	
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	@Override
	public String toString() {
		return "Shows [id=" + id + ", audi=" + audi + ", movie=" + movie + ", time=" + time + ", price=" + price
				+ ", seatMap="+ "]";
	}
}
