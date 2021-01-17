package com.app.bookmymovie.pojo;


import java.util.Arrays;


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
	private Integer number;
	private byte[] seatMap;
	private int totalSeats;
	@ManyToOne
	@JoinColumn(name="theatre_id")
	private Theatre theatre;
	
	public Audi() {
		// TODO Auto-generated constructor stub
	}
	public Audi(Integer number, byte[] seatMap, int totalSeats) {
		super();
		this.number = number;
		this.seatMap = seatMap;
		this.totalSeats = totalSeats;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public byte[] getSeatMap() {
		return seatMap;
	}
	public void setSeatMap(byte[] seatMap) {
		this.seatMap = seatMap;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	@Override
	public String toString() {
		return "Audi [number=" + number + ", seatMap=" + Arrays.toString(seatMap) + ", totalSeats=" + totalSeats + "]";
	}
	

}
