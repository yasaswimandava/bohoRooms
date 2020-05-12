package com.mindtree.bohorooms.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.mindtree.bohorooms.entity.Room;

public class UserDTO {
	private int userId;

	@NotBlank(message = "User Name Can Not Be Empty !")
	private String name;

	private Set<Room> rooms = new HashSet<Room>();

	public UserDTO() {
	}

	public UserDTO(int userId, @NotBlank(message = "User Name Can Not Be Empty !") String name, Set<Room> rooms) {
		this.userId = userId;
		this.name = name;
		this.rooms = rooms;
	}

	public UserDTO(int userId, @NotBlank(message = "User Name Can Not Be Empty !") String name) {
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

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

}
