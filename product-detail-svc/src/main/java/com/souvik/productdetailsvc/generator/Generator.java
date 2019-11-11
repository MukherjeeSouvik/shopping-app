package com.souvik.productdetailsvc.generator;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.souvik.productdetailsvc.bean.ProductDetails;
import com.souvik.productdetailsvc.repository.ProductDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Generator {
	
	@Autowired
	private ProductDetailsRepository repository;
	
	@Transactional
	@PostConstruct
	void init() {
		log.info("Generating Product Data");
		
		List<ProductDetails> products = Arrays.asList(
				ProductDetails.builder().productId("1").name("Product-1").description("Product 1 description").manufacturer("MAN1").build(),
				ProductDetails.builder().productId("2").name("Product-2").description("Product 2 description").manufacturer("MAN1").build(),
				ProductDetails.builder().productId("3").name("Product-3").description("Product 3 description").manufacturer("MAN1").build(),
				ProductDetails.builder().productId("4").name("Product-4").description("Product 4 description").manufacturer("MAN1").build(),
				ProductDetails.builder().productId("5").name("Product-5").description("Product 5 description").manufacturer("MAN1").build()
			);
		
		repository.saveAll(products);
		log.info("Product Data generated");
	}

}
