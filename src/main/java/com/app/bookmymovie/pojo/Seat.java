package com.app.bookmymovie.pojo;

import javax.persistence.Embeddable;

@Embeddable
public class Seat {
	int row ;
	char column ;
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public char getColumn() {
		return column;
	}
	public void setColumn(char column) {
		this.column = column;
	}
	public Seat(int row, char column) {
		super();
		this.row = row;
		this.column = column;
	}
	public Seat() {
		super();
	}
	@Override
	public String toString() {
		return "Seat [row=" + row + ", column=" + column + "]";
	}
	
}
