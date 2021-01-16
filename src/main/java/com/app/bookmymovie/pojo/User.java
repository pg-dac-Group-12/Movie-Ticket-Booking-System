package com.app.bookmymovie.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_tbl")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	private String name ;
	private String email; 
	private String password ;
	private String phone_no ;
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Ticket> tickets=new ArrayList<>();
	
	public User() {
		super();
	}
	public User( String name, String email, String password, String phone_no) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone_no = phone_no;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
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
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone_no="
				+ phone_no + "]";
	}

}
