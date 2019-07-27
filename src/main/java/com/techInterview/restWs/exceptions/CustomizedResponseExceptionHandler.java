package com.techInterview.restWs.exceptions;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handledAllException(Exception ex, WebRequest request){
		ExceptionDetails  exd = new ExceptionDetails(ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity(exd, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(TreatmentException.class)
	public final ResponseEntity<Object> handleStringTreatmentBadRequest(TreatmentException ex, WebRequest request){
		ExceptionDetails exd = new ExceptionDetails(ex.getMessage(), request.getDescription(false), new Date());
		
		return new ResponseEntity(exd, HttpStatus.BAD_REQUEST);
	}
}
