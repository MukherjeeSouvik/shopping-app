package com.souvik.productfrontendsvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souvik.productfrontendsvc.bean.Product;
import com.souvik.productfrontendsvc.exception.ProductException;
import com.souvik.productfrontendsvc.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productDetailsRepository;

	public List<Product> getProducts() throws ProductException {
		 List<Product> products = productDetailsRepository.getProduct();
		 return products;
	}
	
	public Product getProductById(String productId) throws ProductException {
		Product product = productDetailsRepository.getProductById(productId);
		return product;
	}

}
