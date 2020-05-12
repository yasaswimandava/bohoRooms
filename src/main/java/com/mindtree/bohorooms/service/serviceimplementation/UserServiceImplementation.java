package com.mindtree.bohorooms.service.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bohorooms.dto.UserDTO;
import com.mindtree.bohorooms.entity.Checkout;
import com.mindtree.bohorooms.entity.Hotel;
import com.mindtree.bohorooms.entity.Rating;
import com.mindtree.bohorooms.entity.Room;
import com.mindtree.bohorooms.entity.User;
import com.mindtree.bohorooms.exception.serviceexception.BohoRoomsApplicationServiceException;
import com.mindtree.bohorooms.exception.serviceexception.customexception.HotelNotFoundException;
import com.mindtree.bohorooms.exception.serviceexception.customexception.RatingException;
import com.mindtree.bohorooms.exception.serviceexception.customexception.RoomAlreadyCheckedOutException;
import com.mindtree.bohorooms.exception.serviceexception.customexception.RoomAlreadyExistsException;
import com.mindtree.bohorooms.exception.serviceexception.customexception.RoomNotFoundException;
import com.mindtree.bohorooms.exception.serviceexception.customexception.ThisroomisNotAvailableExcepton;
import com.mindtree.bohorooms.exception.serviceexception.customexception.UserNotFoundException;
import com.mindtree.bohorooms.repository.CheckoutRepository;
import com.mindtree.bohorooms.repository.HotelRepository;
import com.mindtree.bohorooms.repository.RatingRepository;
import com.mindtree.bohorooms.repository.RoomRepository;
import com.mindtree.bohorooms.repository.UserRepository;
import com.mindtree.bohorooms.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	RoomRepository roomrepository;
	@Autowired
	UserRepository userrepository;
	@Autowired
	HotelRepository hotelrepository;
	@Autowired
	CheckoutRepository checkrepository;
	@Autowired
	RatingRepository raterepository;

	@Override
	public User userRegister(User user, Set<Room> rooms) throws BohoRoomsApplicationServiceException {
		User newuser = new User();
		newuser.setName(user.getName());

		for (Room room : rooms) {
			Room ro = roomrepository.findById(room.getId()).get();
			if (ro.getUser() == null) {
				ro.setUser(newuser);
				newuser.setRooms(ro);
				roomrepository.save(ro);
				newuser.setUserId(ro.getUser().getUserId());
			} else {
				userrepository.save(newuser);
				throw new ThisroomisNotAvailableExcepton("room is not available");
			}
		}
		userrepository.save(newuser);

		return newuser;
	}

	@Override
	public Map<String, Set<User>> getUsersInParticularFacility(@Valid int hotelId)
			throws BohoRoomsApplicationServiceException {
		Hotel existHotel = hotelrepository.findById(hotelId)
				.orElseThrow(() -> new HotelNotFoundException("hotel with that id not exists"));
		Map<String, Set<User>> tempusers = new LinkedHashMap<String, Set<User>>();
		Set<Room> temprooms = existHotel.getRooms();
		Set<User> temp2 = new HashSet<User>();
		for (Room room : temprooms) {
			User user = room.getUser();
			temp2.add(user);
		}
		tempusers.put(existHotel.getHotelName(), temp2);

		return tempusers;
	}

	@Override
	public User getCheckOutForm(int userId, int roomid, double rating) throws BohoRoomsApplicationServiceException {
		if (rating > 5) {
			throw new RatingException("rating should be less than or equal to 5");
		}
		User use = userrepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("user with such id not exists"));
		Room rom = roomrepository.findById(roomid)
				.orElseThrow(() -> new RoomNotFoundException("room with such id not exists exception"));
		if (rom.getUser() == null) {
			throw new RoomAlreadyCheckedOutException("room already checkout");
		}
		Rating rate = new Rating();
		rate.setRating(rating);
		rate.setHotel(rom.getHotel());
		raterepository.save(rate);

		rom.setUser(null);
		Hotel checkhotel = rom.getHotel();
		Checkout newCheckOutForm = new Checkout();
		newCheckOutForm.setHotel(checkhotel);
		newCheckOutForm.setUser(use);
		checkrepository.save(newCheckOutForm);
		roomrepository.save(rom);
		use.getRooms().remove(rom);
		return use;
	}

	@Override
	public User getCheckInForm(int userId, int roomId) throws BohoRoomsApplicationServiceException {
		User use = userrepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("user with that id not exists"));
		Room room = roomrepository.findById(roomId)
				.orElseThrow(() -> new RoomNotFoundException("room with such id not exists"));
		User user = room.getUser();
		if (user != null)
			throw new RoomAlreadyExistsException("room is already booked");
		else
			room.setUser(use);
		use.setRooms(room);

		return use;
	}

	@Override
	public List<User> getUpComingUsers() {
		List<User> users = new ArrayList<User>();
		List<Room> rooms = roomrepository.findAll();
		List<User> users1 = userrepository.findAll();
		for (User u : users1) {
			for (Room room : rooms) {
				if (room.getUser() == u) {
					users.add(u);
				}
			}

		}
		return users;
	}

	@Override
	public Map<Integer, User> getCheckedOutUsers() {
		List<Checkout> checkout1 = checkrepository.findAll();
		Map<Integer, User> users = new HashMap<Integer, User>();
		for (Checkout ch : checkout1) {

			users.put(ch.getId(), ch.getUser());
		}
		return users;
	}

	

}
