package com.app.bookmymovie.pojo;


import java.util.Arrays;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_tbl")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String director;
	private String[] cast;
	private long duration; // duration minutes
	private double rating;
	private String icon;
	private int totalShows;
	private String iconContentType;
	
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(String title, String director, String[] cast, long duration, double rating, String icon, int totalShows) {
		super();
		this.title = title;
		this.director = director;
		this.cast = cast;
		this.duration = duration;
		this.rating = rating;
		this.icon = icon;
		this.totalShows = totalShows;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String[] getCast() {
		return cast;
	}
	public void setCast(String[] cast) {
		this.cast = cast;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getTotalShows() {
		return totalShows;
	}
	public void setTotalShows(int totalShows) {
		this.totalShows = totalShows;
	}
	
	public String getIconContentType() {
		return iconContentType;
	}
	public void setIconContentType(String iconContentType) {
		this.iconContentType = iconContentType;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", cast=" + Arrays.toString(cast)
				+ ", duration=" + duration + ", rating=" + rating + ", icon=" + icon + ", totalShows="
				+ totalShows + "]";
	}
}
