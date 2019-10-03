package com.souvik.productorchsvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.souvik.productorchsvc.bean.Product;
import com.souvik.productorchsvc.bean.Stock;
import com.souvik.productorchsvc.exception.ProductOrchException;
import com.souvik.productorchsvc.service.StockService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductController {
	
	@Autowired
	private StockService stockService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getStocks() throws ProductOrchException {
		log.info("Retrieving all product details");
		List<Product> products = new ArrayList<>();
		List<Stock> stocks = stockService.getStocks();
		if (!CollectionUtils.isEmpty(stocks)) {
			stocks.forEach(stock -> products.add(Product.builder().stock(stock).build()));
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getStockById(@PathVariable("productId") String productId) throws ProductOrchException {
		log.info("Retrieving product details for id {}", productId);
		Product product = null;
		Stock stock = stockService.getStockById(productId);
		if (null != stock) {
			product = Product.builder().stock(stock).build();
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

}
