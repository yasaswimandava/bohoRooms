package com.mindtree.bohorooms.controller.controlleradvice;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.bohorooms.dto.ErrorDTO;
import com.mindtree.bohorooms.dto.ResponseBody;
import com.mindtree.bohorooms.exception.BohoRoomsApplicationException;
import com.mindtree.bohorooms.exception.serviceexception.customexception.HotelNotFoundException;


@RestControllerAdvice
public class BohoRoomdControllerAdvice {

	@ExceptionHandler(BohoRoomsApplicationException.class)
	public ResponseEntity<?> errorHandler(Exception e) {
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, new ErrorDTO(e.getMessage(), e.getCause()), "Error in Application", false),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		String errorMessaage = "";
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			errorMessaage += error.getDefaultMessage() + ", ";
		}

		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, new ErrorDTO(errorMessaage, ex.getCause()), "Error in Application", false),
				HttpStatus.BAD_REQUEST);
}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<?> errorSQLHandler(Exception e) {
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, new ErrorDTO(e.getMessage(), e.getCause()), "Error in Application", false),
				HttpStatus.EXPECTATION_FAILED);
		
	}

	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<?> hotelHandler(Exception e) {
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, new ErrorDTO(e.getMessage(), e.getCause()), "Error in Application", false),
				HttpStatus.ALREADY_REPORTED);
	}
	
}
