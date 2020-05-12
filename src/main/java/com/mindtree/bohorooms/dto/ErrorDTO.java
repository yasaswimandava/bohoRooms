package com.mindtree.bohorooms.dto;

public class ErrorDTO {
	private String errormessage;
	private Throwable cause;

	public ErrorDTO() {
	}

	public ErrorDTO(String errormessage, Throwable cause) {
		this.errormessage = errormessage;
		this.cause = cause;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
