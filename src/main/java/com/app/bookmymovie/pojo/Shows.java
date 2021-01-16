package com.app.bookmymovie.pojo;

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
	private Theatre threatre;
	private LocalTime time;
	private double price;
	private byte[] seatMap;
	public Shows(Audi audi, Movie movie, LocalTime time, double price, byte[] seatMap) {
		super();
		this.audi = audi;
		this.movie = movie;
		this.time = time;
		this.price = price;
		this.seatMap = seatMap;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Shows [id=" + id + ", audi=" + audi + ", movie=" + movie + ", time=" + time + ", price=" + price
				+ ", seatMap=" + Arrays.toString(seatMap) + "]";
	}
}
