package com.mindtree.bohorooms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Room_Id")
	private int id;

	@Column(name = "cost")
	private double cost;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.PERSIST)
	private User user;

	@JsonIgnore
	@ManyToOne
	private Hotel hotel;

	public Room() {
		
	}

	public Room(int id, double cost, User user, Hotel hotel) {
		this.id = id;
		this.cost = cost;
		this.user = user;
		this.hotel = hotel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	

}
