package com.mindtree.bohorooms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_Id")
	private int userId;

	@Column(name = "Name")
	private String name;

	@OneToMany(mappedBy = "user")
	private Set<Room> rooms = new HashSet<Room>();

	public User() {
		
	}

	public User(int userId, String name, Set<Room> rooms) {
		this.userId = userId;
		this.name = name;
		this.rooms = rooms;
	}

	public User(int userId, String name) {
		this.userId = userId;
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Room room) {
		this.rooms.add(room);
	}

}


