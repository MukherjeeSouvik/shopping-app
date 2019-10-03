package com.souvik.productstocksvc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.souvik.productstocksvc.bean.Stock;

//public interface StockRepository extends JpaRepository<Stock, String> {
//
//}
@Component
public class StockRepository {
	
	private List<Stock> stocks = new ArrayList<>();

	public void saveAll(List<Stock> stocks) {
		this.stocks.addAll(stocks);
	}

	public List<Stock> findAll() {
		return this.stocks;
	}

	public Optional<Stock> findById(String productId) {
		return stocks.stream().filter(s -> s.getProductId().equals(productId)).findAny();
	}
	
	
	
}
