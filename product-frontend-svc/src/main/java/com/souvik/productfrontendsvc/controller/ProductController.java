package com.souvik.productfrontendsvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.souvik.productfrontendsvc.exception.ProductException;
import com.souvik.productfrontendsvc.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public String getStocks(Model model) throws ProductException {
		log.info("Retrieving all product details");
		model.addAttribute("products", productService.getProducts());
		return "index";
	}
	

}
