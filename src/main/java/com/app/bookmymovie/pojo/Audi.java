package com.app.bookmymovie.pojo;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Audi {
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;*/
	private Integer number;
	@Embedded
	private List<Seat> seatMap = new ArrayList<>();
	private int totalSeats;

	private Theatre theatre;

	public Audi(Integer number, List<Seat> seatMap, int totalSeats) {
		super();
		this.number = number;
		this.seatMap = seatMap;
		this.totalSeats = totalSeats;
	}
	
	/*public Integer getId() {
		return id;
	}*/

	/*public void setId(Integer id) {
		this.id = id;
	}*/

	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public List<Seat> getSeatMap() {
		return seatMap;
	}
	public void setSeatMap(List<Seat> seatMap) {
		this.seatMap = seatMap;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	@Override
	public String toString() {
		return "Audi [number=" + number + ", seatMap=" + seatMap + ", totalSeats=" + totalSeats + "]";
	}
	

}
