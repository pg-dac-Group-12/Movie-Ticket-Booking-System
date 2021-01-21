package com.app.bookmymovie.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;

@Embeddable
public class Seat implements Cloneable{ 
	@Column(name = "r_number")
	int rowNumber ;
	@Column(name = "c_number")
	char colNumber ; 
	@Column(length = 20, nullable = false)
	@Type(type="yes_no")
	boolean isBooked = false ;
	
	public Seat() {
		super();
	}

	public Seat(int row, char column) {
		super();
		this.rowNumber = row;
		this.colNumber = column;
		
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int row) {
		this.rowNumber = row;
	}
	public char getColNumber() {
		return colNumber;
	}
	public void setColNumber(char column) {
		this.colNumber = column;
	}
	
	public void setSeatStatus() {
		this.isBooked = true ;		
	}
	
	public boolean  getSeatStatus() {
		return this.isBooked ;
	}
	
	public Object clone() {
		return new Seat(this.rowNumber,this.colNumber);
	}
	@Override
	public String toString() {
		return rowNumber + " " + colNumber + " " ;
	}
	
}
