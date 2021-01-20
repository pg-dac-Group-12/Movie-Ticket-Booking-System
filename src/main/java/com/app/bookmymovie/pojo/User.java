package com.app.bookmymovie.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_tbl")
public class User extends Actor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	private String phoneNo ;
	@OneToMany(mappedBy="user",fetch = FetchType.EAGER)
	@JsonIgnoreProperties("user")
	private List<Ticket> tickets=new ArrayList<>();
	
	public User() {
		super.setRole(Role.USER);
	}
	public User( String name, String email, String password, String phoneNo) {
		super(name,email,password,Role.USER);
		this.phoneNo = phoneNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public List<Ticket> getTickes() {
		return tickets;
	}
	public void setTickets(List<Ticket> ticket) {
		this.tickets = ticket;
	}
	
	//Helper methods to add and remove ticket
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
		ticket.setUser(this);
	}
	public void removeTicket(Ticket ticket) {
		tickets.remove(ticket);
		ticket.setUser(null);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + ", email=" + ", password=" +  ", phone_no="
				+ phoneNo + "]";
	}

}
