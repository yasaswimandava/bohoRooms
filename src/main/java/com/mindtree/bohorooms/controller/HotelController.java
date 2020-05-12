package com.mindtree.bohorooms.controller;

import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.bohorooms.dto.HotelDTO;
import com.mindtree.bohorooms.dto.ResponseBody;
import com.mindtree.bohorooms.entity.Hotel;
import com.mindtree.bohorooms.exception.BohoRoomsApplicationException;
import com.mindtree.bohorooms.service.HotelService;

@RestController
public class HotelController {
	@Autowired
	private HotelService hotelService;
	@Autowired
	private ModelMapper modelmapper;

	@PostMapping(value = "/hotel")
	public ResponseEntity<?> addHotel(@Valid @RequestBody HotelDTO hotel) throws BohoRoomsApplicationException {

		return new ResponseEntity<ResponseBody<HotelDTO>>(new ResponseBody<HotelDTO>(
				modelmapper.map(hotelService.addHotelData(modelmapper.map(hotel, Hotel.class)), HotelDTO.class), null,
				"Hotel Add  Sucessfully", true), HttpStatus.OK);
	}

	@GetMapping(value = "/GetHotels/{amount}")
	public ResponseEntity<?> getAllTheHotelsExceedsGreaterThanValue(@Valid @PathVariable double amount) {
		return new ResponseEntity<ResponseBody<Map<String, Double>>>(
				new ResponseBody<Map<String, Double>>(modelmapper.map(
						hotelService.getAllHotelsWithExceededAmount(amount), new TypeToken<Map<String, Double>>() {
						}.getType()), null, "hotels with exceeded amount", true),
				HttpStatus.OK);

	}

	@GetMapping(value = "/SumOfRevenues")
	public ResponseEntity<?> getSumOfRevenuesOfLivingFacility() {
		return new ResponseEntity<Object>(
				new ResponseBody<Object>(hotelService.getRevenues(), null, "total revenues of the bohorooms", true),
				HttpStatus.OK);

	}

	@GetMapping(value = "/AverageOfrevenues")
	public ResponseEntity<?> getAverageOfEachRevenueFacility() {
		return new ResponseEntity<ResponseBody<Object>>(
				new ResponseBody<Object>(hotelService.averageOfeachRevenue(), null, "average revenue of hotels", true),
				HttpStatus.OK);

	}

	@GetMapping(value = "/FacilitiesAndRevenues")
	public ResponseEntity<?> getFacilitiesAndRevenues() {
		return new ResponseEntity<ResponseBody<Map<String, Double>>>(new ResponseBody<Map<String, Double>>(
				modelmapper.map(hotelService.getAllFacilitiesAndRevenues(), new TypeToken<Map<String, Double>>() {
				}.getType()), null, "hotels with their revenues", true), HttpStatus.OK);

	}

	@GetMapping(value = "/GetUserBookedGreaterThanTwo")
	public ResponseEntity<?> getUserBookedGreaterThanTwo() {
		return new ResponseEntity<ResponseBody<Map<String, Integer>>>(new ResponseBody<Map<String, Integer>>(modelmapper
				.map(hotelService.getAllTheUsersBookedgreaterThanTwo(), new TypeToken<Map<String, Double>>() {
				}.getType()), null, "users who bookes greater than two", true), HttpStatus.OK);
	}

	@GetMapping(value = "/GetRatingGreaterThanFour")
	public ResponseEntity<?> getUserBookedGreaterThanTwoAndRatingMoreThanFour() {
		return new ResponseEntity<ResponseBody<Map<String, Double>>>(new ResponseBody<Map<String, Double>>(
				modelmapper.map(hotelService.getAllgetUserBookedGreaterThanTwoAndRatingMoreThanFour(),
						new TypeToken<Map<String, Double>>() {
						}.getType()),
				null, "users who bookes greater than two & rating more than four", true), HttpStatus.OK);

	}

}
