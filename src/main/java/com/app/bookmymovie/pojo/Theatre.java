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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "theatre_tbl")
public class Theatre extends Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String city;
	private String location;
	@OneToMany(mappedBy="theatre", cascade=CascadeType.ALL)
	@JsonIgnoreProperties("theatre")
	private List<Audi> audis = new ArrayList<>();

	public Theatre() {
		super.setRole(Role.THEATRE);
	};

	public Theatre(String name ,String email, String password, String city, String location) {
		super(name,email,password,Role.THEATRE);
		this.city = city;
		this.location = location;
	}

	public List<Audi> getAudis() {
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

	public Audi getAudiByNumber(int audiId) {
		for (Audi audi : audis) {
			if (audiId == audi.getId())
				return audi;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Theatre [id=" + id + ", email=" + ", password=" + ", name=" + ", city=" + city
				+ ", location=" + location + "]";
	}
}
