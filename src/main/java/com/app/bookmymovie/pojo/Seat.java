package com.app.bookmymovie.pojo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Seat implements Cloneable{
	@Column(name = "r_number")
	int rowNumber ;
	@Column(name = "c_number")
	char colNumber ;
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
	public Seat(int row, char column) {
		super();
		this.rowNumber = row;
		this.colNumber = column;
	}
	public Seat() {
		super();
	}
	
	public Object clone() {
		return new Seat(this.rowNumber,this.colNumber);
	}
	@Override
	public String toString() {
		return "Seat [row=" + rowNumber + ", column=" + colNumber + "]";
	}
	
}
