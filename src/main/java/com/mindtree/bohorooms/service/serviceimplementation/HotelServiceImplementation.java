package com.mindtree.bohorooms.service.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bohorooms.dto.RoomDTO;
import com.mindtree.bohorooms.entity.Hotel;
import com.mindtree.bohorooms.entity.Rating;
import com.mindtree.bohorooms.entity.Room;
import com.mindtree.bohorooms.exception.serviceexception.BohoRoomsApplicationServiceException;
import com.mindtree.bohorooms.exception.serviceexception.customexception.HotelAlreadyExistsException;
import com.mindtree.bohorooms.repository.HotelRepository;
import com.mindtree.bohorooms.repository.RatingRepository;
import com.mindtree.bohorooms.repository.RoomRepository;
import com.mindtree.bohorooms.service.HotelService;

@Service
public class HotelServiceImplementation implements HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Hotel addHotelData(Hotel hotel) throws BohoRoomsApplicationServiceException {
		if (hotelRepository.findByhotelName(hotel.getHotelName()).isPresent()) 
			throw new HotelAlreadyExistsException("hotel with that name already exists ");
		
		Hotel newhotel = new Hotel();
		newhotel.setHotelName(hotel.getHotelName());
		newhotel.setRooms(hotel.getRooms());
		hotelRepository.save(newhotel);
		Set<Room> rooms = hotel.getRooms();
		List<RoomDTO> roomdt=new ArrayList<RoomDTO>();
		for (Room rdt : rooms) {
			RoomDTO newroomdt=new RoomDTO();
			newroomdt.setCost(rdt.getCost());
			roomdt.add(newroomdt);
		}
		
		
		rooms.forEach(r->{r.setHotel(newhotel);});
		roomRepository.saveAll(rooms);
        return newhotel;
	}

	@Override
	public Map<String, Double> getAllHotelsWithExceededAmount(@Valid double amount) {
		Map<String,Double> temphotels=new HashMap<String,Double>();
	List<Hotel> listhotels=hotelRepository.findAll();
	for (Hotel hotel : listhotels) {
		Set<Room> rooms=hotel.getRooms();
		List<Room> listrooms=new ArrayList<Room>(rooms);
		double sumOfBookings=listrooms.stream().map(room->room.getCost()).reduce(0.0, Double::sum);
		if(sumOfBookings>amount)
		{
			temphotels.put(hotel.getHotelName(), sumOfBookings);
		}
		
		
	}
		return temphotels;
	}

	@Override
	public double getRevenues() {
		double sum=0,tempsum=0;
		List<Hotel> hotels=hotelRepository.findAll();
		for (Hotel hotel : hotels) {
			Set<Room> rooms=hotel.getRooms();
			List<Room> listrooms=new ArrayList<Room>(rooms);
			tempsum=listrooms.stream().map(room->room.getCost()).reduce(0.0,Double::sum);
			
			sum=sum+tempsum;
			
		}
		return sum;
	}

	@Override
	public double averageOfeachRevenue() {
		Map<Hotel,Double> temphotels=new HashMap<Hotel,Double>();
		double average=0,tempaverage=0;
		List<Hotel> hotels=hotelRepository.findAll();
		for (Hotel hotel : hotels) {
			Set<Room> rooms=hotel.getRooms();
			List<Room> listrooms=new ArrayList<Room>(rooms);
			double sum=listrooms.stream().map(room->room.getCost()).reduce(0.0,Double::sum);
			tempaverage=sum/listrooms.size();
			average=average+tempaverage;
			
			temphotels.put(hotel, sum);
		}
		
		return average;
	}

	@Override
	public Map<String, Double> getAllFacilitiesAndRevenues() {
		Map<String,Double> temphotel=new HashMap<String,Double>();
		List<Hotel> hotels=hotelRepository.findAll();
		for (Hotel hotel : hotels) {
			Set<Room> rooms=hotel.getRooms();
			List<Room> listrooms=new ArrayList<Room>(rooms);
			double sum=listrooms.stream().map(room->room.getCost()).reduce(0.0,Double::sum);
			temphotel.put(hotel.getHotelName(), sum);
			
		}
		
		return temphotel;
	}

	@Override
	public Map<String, Integer> getAllTheUsersBookedgreaterThanTwo() {
		Map<String, Integer> temphotel=new LinkedHashMap<String,Integer>();
		List<Hotel> hotels=hotelRepository.findAll();
		List<Room> rooms=roomRepository.findAll();
		for (Hotel hotel : hotels) {
			int count=0;
			List<Room> temprooms=rooms.stream().filter(rom->rom.getHotel()==hotel).map(rom->rom).collect(Collectors.toList());
			for (Room room : temprooms) {
				if(room.getUser()!=null)
				{
					count++;
				}
				if(count>2)
				{
					temphotel.put(hotel.getHotelName(), count);
				}
			}
			}
		return temphotel;
	}

	@Override
	public Map<String, Double> getAllgetUserBookedGreaterThanTwoAndRatingMoreThanFour() {
		List<Hotel> hotel=hotelRepository.findAll();
		List<Rating> rating=ratingRepository.findAll();
		List<Room> rooms=roomRepository.findAll();
		Map<String,Double> tempusers=new HashMap<String, Double>();

		for(Hotel hotel1:hotel) {
			List<Rating> rating2=rating.stream().filter(r->r.getHotel()==hotel1).map(r->r).collect(Collectors.toList());
			double rating1=rating.stream().filter(r->r.getHotel()==hotel1).map(r->r.getRating()).reduce(0.0, Double::sum);
		    int count=0;
		    double finalRating=rating1/rating2.size();
			List<Room> room=rooms.stream().filter(r->r.getHotel()==hotel1).map(r->r).collect(Collectors.toList());
			for(Room r:room) {
				if(r.getUser()!=null) 
					count++;
				}
		if(count>2) {
			if(finalRating>4)
				tempusers.put(hotel1.getHotelName(), finalRating);
		}
			
			}
		return tempusers;
	}
	
	
}
