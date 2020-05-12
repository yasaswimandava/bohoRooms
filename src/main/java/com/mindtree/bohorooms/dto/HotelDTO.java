package com.mindtree.bohorooms.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.mindtree.bohorooms.entity.Room;

public class HotelDTO {
	private int hotelId;

	@NotBlank(message = "Hotel Name Can Not Be Empty !")
	private String hotelname;

	private Set<RoomDTO> rooms = new HashSet<RoomDTO>();

	public HotelDTO() {
		// TODO Auto-generated constructor stub
	}

	public HotelDTO(int hotelId, @NotBlank(message = "Hotel Name Can Not Be Empty !") String hotelname,
			Set<RoomDTO> rooms) {
		this.hotelId = hotelId;
		this.hotelname = hotelname;
		this.rooms = rooms;
	}

	public HotelDTO(int hotelId, @NotBlank(message = "Hotel Name Can Not Be Empty !") String hotelname) {
		this.hotelId = hotelId;
		this.hotelname = hotelname;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public Set<RoomDTO> getRooms() {
		return rooms;
	}

	public void setRooms(Set<RoomDTO> rooms) {
		this.rooms = rooms;
	}

	

}
