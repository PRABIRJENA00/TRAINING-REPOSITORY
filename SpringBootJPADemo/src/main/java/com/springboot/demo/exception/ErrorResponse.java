package com.springboot.demo.exception;

import java.util.List;

class ErrorResponse {
	private String message;
	private List<String> details;

	public ErrorResponse(String message, List<String> details) {
		super();
		this.details = details;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}