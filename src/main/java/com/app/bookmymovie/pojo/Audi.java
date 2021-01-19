package com.app.bookmymovie.pojo;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "audi_tbl")
public class Audi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer number;
	@Embedded
	@ElementCollection
	@CollectionTable(name = "audi_seats_tbl" , joinColumns = @JoinColumn(name = "audi_id"))
	private List<Seat> seatMap = new ArrayList<>();
	private int totalSeats;
	@ManyToOne()
	@JoinColumn(name="theatre_id")
	@JsonIgnoreProperties("audis")
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
	
	public void modifyAudi ( Audi newAudi) {
		this.number = newAudi.number ;
		this.seatMap = newAudi.seatMap ;
		this.totalSeats = newAudi.totalSeats;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public List<Seat> getSeatMap() {
		List<Seat> clonedSeatMap = new ArrayList<>() ;
		for(Seat seat : this.seatMap) {
			clonedSeatMap.add((Seat)seat.clone());
		}
		return clonedSeatMap;
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
