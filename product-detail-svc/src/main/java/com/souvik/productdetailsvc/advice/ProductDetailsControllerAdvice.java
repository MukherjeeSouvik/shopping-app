package com.souvik.productdetailsvc.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.souvik.productdetailsvc.bean.ErrorResponse;
import com.souvik.productdetailsvc.util.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ProductDetailsControllerAdvice {
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ErrorResponse> handleExceptions(Exception exception, HttpServletRequest httpServletRequest) {
		ErrorResponse errorResponse = new ErrorResponse(AppConstants.SERVER_ERROR, exception);
		log.error(AppConstants.INTERNAL_SERVER_ERROR_MSG, 
				httpServletRequest.getRequestURL(), httpServletRequest.getQueryString(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
