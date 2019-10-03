package com.souvik.productstocksvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souvik.productstocksvc.bean.Stock;
import com.souvik.productstocksvc.exception.StockException;
import com.souvik.productstocksvc.repository.StockRepository;

@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> getStockDetails() {
		return stockRepository.findAll();
	}
	
	public Stock getStockDetailsById(String productId) throws StockException {
		return stockRepository.findById(productId)
				.orElseThrow(() -> new StockException("Stock Details not found for id" + productId));
	}

}
