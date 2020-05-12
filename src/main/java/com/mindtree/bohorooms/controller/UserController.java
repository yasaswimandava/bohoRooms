package com.mindtree.bohorooms.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.bohorooms.dto.ResponseBody;
import com.mindtree.bohorooms.dto.UserDTO;
import com.mindtree.bohorooms.entity.User;
import com.mindtree.bohorooms.exception.BohoRoomsApplicationException;
import com.mindtree.bohorooms.service.RoomService;
import com.mindtree.bohorooms.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userservice;
	@Autowired
	RoomService roomservice;
	@Autowired
	ModelMapper modelmapper;

	@PostMapping("/Register/user")
	public ResponseEntity<?> userRegister(@RequestBody UserDTO user) throws BohoRoomsApplicationException {
		return new ResponseEntity<ResponseBody<UserDTO>>(
				new ResponseBody<UserDTO>(
						modelmapper.map(userservice.userRegister(modelmapper.map(user, User.class), user.getRooms()),
								UserDTO.class),
						null, " user registred  succesffuly and successfully rooms are booked", true),
				HttpStatus.OK);

	}

	@GetMapping("/LivingFacility/{hotelId}")
	public ResponseEntity<?> usersInParticularLivingFacility(@Valid @PathVariable int hotelId)
			throws BohoRoomsApplicationException {
		return new ResponseEntity<ResponseBody<Map<String, Set<User>>>>(
				new ResponseBody<Map<String, Set<User>>>(modelmapper.map(
						userservice.getUsersInParticularFacility(hotelId), new TypeToken<Map<String, Set<User>>>() {
						}.getType()), null, "users found", true),
				HttpStatus.OK);

	}

	@PutMapping("/CheckOutForm/{userId}/{roomid}/{rating}")
	public ResponseEntity<?> checkOutForm(@PathVariable int userId, @PathVariable int roomid,
			@PathVariable double rating) throws BohoRoomsApplicationException {
		return new ResponseEntity<ResponseBody<User>>(
				new ResponseBody<User>(userservice.getCheckOutForm(userId, roomid, rating), null,
						"user check outed succcessfully", true),
				HttpStatus.OK);

	}

	@PutMapping("/CheckIn/{userId}/{roomId}")
	public ResponseEntity<?> userCheckInForm(@PathVariable int userId, @PathVariable int roomId)
			throws BohoRoomsApplicationException {
		return new ResponseEntity<ResponseBody<User>>(new ResponseBody<User>(userservice.getCheckInForm(userId, roomId),
				null, "user checked In successfully", true), HttpStatus.OK);

	}

	@GetMapping(value = "/UpComingBookings")
	public ResponseEntity<?> getUpComingBookings() {
		return new ResponseEntity<ResponseBody<List<User>>>(new ResponseBody<List<User>>(
				modelmapper.map(userservice.getUpComingUsers(), new TypeToken<List<User>>() {
				}.getType()), null, "up coming bookings", true), HttpStatus.OK);

	}

	@GetMapping(value = "/getListOfChekOutUsers")
	public ResponseEntity<?> getListOfChekOutUsers() {
		return new ResponseEntity<ResponseBody<Map<Integer, User>>>(new ResponseBody<Map<Integer, User>>(
				modelmapper.map(userservice.getCheckedOutUsers(), new TypeToken<Map<Integer, User>>() {
				}.getType()), null, "Users who checked out", true), HttpStatus.OK);

	}

}
