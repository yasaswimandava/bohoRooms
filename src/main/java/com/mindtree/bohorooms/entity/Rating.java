package com.mindtree.bohorooms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Rating_Id")
	private int id;

	@Column(name = "Ratings")
	private double rating;

	@OneToOne
	private Hotel hotel;

	public Rating() {
		// TODO Auto-generated constructor stub
	}

	public Rating(int id, double rating, Hotel hotel) {
		this.id = id;
		this.rating = rating;
		this.hotel = hotel;
	}

	public Rating(int id, double rating) {
		this.id = id;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
