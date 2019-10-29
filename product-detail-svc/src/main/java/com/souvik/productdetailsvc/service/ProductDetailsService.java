package com.souvik.productdetailsvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souvik.productdetailsvc.bean.ProductDetails;
import com.souvik.productdetailsvc.exception.ProductDetailsException;
import com.souvik.productdetailsvc.repository.ProductDetailsRepository;

@Service
public class ProductDetailsService {
	
	@Autowired
	private ProductDetailsRepository repository;
	
	public ProductDetails getProductDetailsById(String productId) throws ProductDetailsException {
		return repository
				.findById(productId)
				.orElseThrow(() -> new ProductDetailsException("Product Details not found for id" + productId));
	}
	
	public List<ProductDetails> getProductDetails() {
		return repository.findAll();
	}

}
