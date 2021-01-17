package com.app.bookmymovie.pojo;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "audi_tbl")
public class Audi {
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;*/
	private Integer number;
	@Embedded
	private List<Seat> seatMap = new ArrayList<>();
	private int totalSeats;
	@ManyToOne
	@JoinColumn(name="theatre_id")
	private Theatre theatre;

	public Audi() {
		// TODO Auto-generated constructor stub
	}
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
