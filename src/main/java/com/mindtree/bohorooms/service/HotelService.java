package com.mindtree.bohorooms.service;

import java.util.Map;

import javax.validation.Valid;

import com.mindtree.bohorooms.entity.Hotel;
import com.mindtree.bohorooms.exception.serviceexception.BohoRoomsApplicationServiceException;

public interface HotelService {

	Hotel addHotelData(Hotel hotel) throws BohoRoomsApplicationServiceException;

	Map<String,Double> getAllHotelsWithExceededAmount(@Valid double amount);

	double getRevenues();

	double averageOfeachRevenue();

	

	Map<String ,Double> getAllFacilitiesAndRevenues();

	Map<String, Integer> getAllTheUsersBookedgreaterThanTwo();

	Map<String, Double> getAllgetUserBookedGreaterThanTwoAndRatingMoreThanFour();
	

}
