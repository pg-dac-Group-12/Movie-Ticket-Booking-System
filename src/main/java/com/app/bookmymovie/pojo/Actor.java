package com.app.bookmymovie.pojo;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Actor  {
	private String name;
	private String email; 
	private String password ;
	private Role role ;

	public Actor() {
		super();
	}
	public Actor(String name, String email, String password, Role role) {
		super();
		this.name = name ;
		this.email = email;
		this.password = password;
		this.role = role ;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
