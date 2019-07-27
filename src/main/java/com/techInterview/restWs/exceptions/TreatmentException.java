package com.techInterview.restWs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TreatmentException extends RuntimeException {
	
	public TreatmentException(String arg0) {
		super(arg0);
	}

}
