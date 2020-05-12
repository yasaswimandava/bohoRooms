package com.mindtree.bohorooms.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import com.mindtree.bohorooms.dto.UserDTO;
import com.mindtree.bohorooms.entity.Room;
import com.mindtree.bohorooms.entity.User;
import com.mindtree.bohorooms.exception.serviceexception.BohoRoomsApplicationServiceException;

public interface UserService {

	User userRegister(User user, Set<Room> rooms) throws BohoRoomsApplicationServiceException;

	Map<String,Set<User>> getUsersInParticularFacility(@Valid int hotelId) throws BohoRoomsApplicationServiceException;

	User getCheckOutForm(int userId, int roomid, double rating) throws BohoRoomsApplicationServiceException;

	User getCheckInForm(int userId, int roomId) throws BohoRoomsApplicationServiceException;

	List<User> getUpComingUsers();

	Map<Integer,User> getCheckedOutUsers();


	

}
