package com.souvik.productorchsvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.souvik.productorchsvc.bean.Product;
import com.souvik.productorchsvc.exception.ProductOrchException;
import com.souvik.productorchsvc.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getStocks() throws ProductOrchException {
		log.info("Retrieving products");
		return new ResponseEntity<List<Product>>(productService.getProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getStockById(@PathVariable("productId") String productId) throws ProductOrchException {
		log.info("Retrieving product for id {}", productId);
		return new ResponseEntity<Product>(productService.getProductById(productId), HttpStatus.OK);
	}

}
