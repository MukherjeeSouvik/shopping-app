package com.souvik.productstocksvc.generator;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.souvik.productstocksvc.bean.Stock;
import com.souvik.productstocksvc.repository.StockRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StockGenerator {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Transactional
	@PostConstruct
	void init() {
		log.info("Generating Stock Data");
		
		List<Stock> stocks = Arrays.asList(
			Stock.builder().productId("1").sku("1234567890").quantity(50L).build(),
			Stock.builder().productId("2").sku("1234567891").quantity(51L).build(),
			Stock.builder().productId("3").sku("1234567892").quantity(52L).build(),
			Stock.builder().productId("4").sku("1234567893").quantity(53L).build(),
			Stock.builder().productId("5").sku("1234567894").quantity(54L).build()
		);
		
		stockRepository.saveAll(stocks);
		log.info("Stock Data generated");
	}
	
	
	

}
