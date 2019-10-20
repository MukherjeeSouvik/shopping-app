package com.souvik.productdetailsvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.souvik.productdetailsvc.bean.ProductDetails;
import com.souvik.productdetailsvc.exception.ProductDetailsException;
import com.souvik.productdetailsvc.service.ProductDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductDetailsController {
	
	@Autowired
	private ProductDetailsService productDetailsService;
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDetails>> getProductDetails() throws ProductDetailsException {
		log.info("Retrieving all product details}");
		return new ResponseEntity<List<ProductDetails>>(productDetailsService.getProductDetails(), HttpStatus.OK);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDetails> getProductDetailsById(@PathVariable("productId") String productId) throws ProductDetailsException {
		log.info("Retrieving stock details for id {}", productId);
		return new ResponseEntity<ProductDetails>(productDetailsService.getProductDetailsById(productId), HttpStatus.OK);
	}

}
