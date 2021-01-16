package com.app.bookmymovie.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "theatre_tbl")
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	private String name;
	private String city;
	private String location;
	@OneToMany(mappedBy = "theatre", fetch = FetchType.EAGER)
	private ArrayList<Audi> audis = new ArrayList<>();

	public Theatre() {
	};

	public Theatre(String email, String password, String name, String city, String location) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.city = city;
		this.location = location;
	}

	public ArrayList<Audi> getAudis() {
		return audis;
	}

	public void setAudis(ArrayList<Audi> audis) {
		this.audis = audis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	// helper methods
	public boolean addAudi(Audi audi) {
		int flag = 0;
		for (Audi a : audis)
			if (a.getNumber() == audi.getNumber())
				flag++;
		if (flag == 0) {
			audi.setTheatre(this);
			return audis.add(audi);	
		}
		return false;
	}

	public boolean removeAudi(Audi audi) {
		boolean isRemoved = audis.remove(audi);
		audi.setTheatre(null);
		return isRemoved;
	}

	public Audi getAudiByNumber(int audiNumber) {
		for (Audi audi : audis) {
			if (audiNumber == audi.getNumber())
				return audi;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Theatre [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", city=" + city
				+ ", location=" + location + "]";
	}
}
