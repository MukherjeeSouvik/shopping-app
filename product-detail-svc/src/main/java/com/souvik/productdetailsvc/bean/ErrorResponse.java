package com.souvik.productdetailsvc.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private String code;
	private String message;
	private String error;

	public ErrorResponse(String code, Exception exception) {
		super();
		this.code = code;
		this.message = exception.getLocalizedMessage();
		this.error = exception.getClass().getName();
	}

}
