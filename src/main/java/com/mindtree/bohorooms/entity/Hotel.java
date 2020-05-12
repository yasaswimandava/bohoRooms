package com.mindtree.bohorooms.entity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Hotel")
public class Hotel implements Comparator<Hotel> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Hotel_Id")
	private int hotelId;

	@Column(name = "Hotel_Name")
	private String hotelName;

	@OneToMany(mappedBy = "hotel", cascade = { CascadeType.PERSIST, CascadeType.REMOVE ,})
	private Set<Room> rooms = new HashSet<Room>();

	public Hotel() {
	}

	public Hotel(int hotelId, String hotelName, Set<Room> rooms) {
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.rooms = rooms;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public int compare(Hotel h1, Hotel h2) {
		return h1.getHotelId() - h2.getHotelId();
	} 

}
