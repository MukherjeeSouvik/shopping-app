package com.souvik.productorchsvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souvik.productorchsvc.bean.Stock;
import com.souvik.productorchsvc.exception.ProductOrchException;
import com.souvik.productorchsvc.repository.StockRepository;

@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> getStocks() throws ProductOrchException {
		return stockRepository.getStocks();
	}
	
	public Stock getStockById(String productId) throws ProductOrchException {
		return stockRepository.getStockById(productId);
	}
	
	

}
