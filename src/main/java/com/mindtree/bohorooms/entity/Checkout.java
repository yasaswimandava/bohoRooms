package com.mindtree.bohorooms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Checkout")
public class Checkout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Checkout_Id")
	private int id;

	@OneToOne
	private Hotel hotel;

	@OneToOne
	private User user;

	public Checkout() {
	}

	public Checkout(int id, Hotel hotel, User user) {
		this.id = id;
		this.hotel = hotel;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
