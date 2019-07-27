package com.techInterview.restWs.exceptions;

import java.util.Date;

public class ExceptionDetails {
	private String message;
	private String details;
	private Date timestamp;
	
	public ExceptionDetails() {
		
	}
	
	public ExceptionDetails(String message) {
		this.message = message;
	}

	public ExceptionDetails(String message, String details) {
		this.message = message;
		this.details = details;
	}
	
	public ExceptionDetails(String message, String details, Date timestamp) {
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
