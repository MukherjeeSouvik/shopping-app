package com.souvik.productstocksvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.souvik.productstocksvc.bean.Stock;
import com.souvik.productstocksvc.exception.StockException;
import com.souvik.productstocksvc.service.StockService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@GetMapping("/stocks")
	public ResponseEntity<List<Stock>> getStocks() {
		log.info("Retrieving all stock details");
		List<Stock> stocks = stockService.getStockDetails();
		return new ResponseEntity<List<Stock>>(stocks, HttpStatus.OK);
	}
	
	@GetMapping("/stocks/{productId}")
	public ResponseEntity<Stock> getStockById(@PathVariable("productId") String productId) throws StockException {
		log.info("Retrieving stock details for id {}", productId);
		return new ResponseEntity<Stock>(stockService.getStockDetailsById(productId), HttpStatus.OK);
	}
	
	

}
