package com.souvik.productdetailsvc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.souvik.productdetailsvc.bean.ProductDetails;
import com.souvik.productdetailsvc.exception.ProductDetailsException;

@Service
public class ProductDetailsService {
	
	private List<ProductDetails> products = new ArrayList<>();
	
	public ProductDetails getProductDetailsById(String productId) throws ProductDetailsException {
		return products.stream()
				.filter(product -> productId.equalsIgnoreCase(product.getProductId()))
				.findFirst()
				.orElseThrow(() -> new ProductDetailsException("Stock Details not found for id" + productId));
	}
	
	@PostConstruct
	void init() {
		products.addAll(Arrays.asList(
			ProductDetails.builder().productId("1").name("Product-1").description("Product 1 description").price(BigDecimal.valueOf(45.56)).build(),
			ProductDetails.builder().productId("2").name("Product-2").description("Product 2 description").price(BigDecimal.valueOf(45.55)).build(),
			ProductDetails.builder().productId("3").name("Product-3").description("Product 3 description").price(BigDecimal.valueOf(45.54)).build(),
			ProductDetails.builder().productId("4").name("Product-4").description("Product 4 description").price(BigDecimal.valueOf(45.53)).build(),
			ProductDetails.builder().productId("5").name("Product-5").description("Product 5 description").price(BigDecimal.valueOf(45.52)).build()
		));
	}

	public List<ProductDetails> getProductDetails() {
		return products;
	}

}
