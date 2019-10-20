package com.souvik.productorchsvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.souvik.productorchsvc.bean.Product;
import com.souvik.productorchsvc.bean.ProductDetails;
import com.souvik.productorchsvc.bean.Stock;
import com.souvik.productorchsvc.exception.ProductOrchException;
import com.souvik.productorchsvc.repository.ProductDetailsRepository;
import com.souvik.productorchsvc.repository.StockRepository;

@Service
public class ProductService {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	public List<Product> getProducts() throws ProductOrchException {
		 List<Product> products = new ArrayList<>();
		 
		 Map<String, ProductDetails> productDetails = productDetailsRepository.getProductDetailsMap();
		 Map<String, Stock> stocks = stockRepository.getStockMap();
		 
		 if (!CollectionUtils.isEmpty(productDetails)) {
			 products = productDetails.values()
					 			.stream()
				 				.map(pd -> {
				 					Stock stock = stocks.get(pd.getProductId());
				 					return Product.builder()
				 							.productId(pd.getProductId())
				 							.name(pd.getName())
				 							.description(pd.getDescription())
				 							.price(pd.getPrice())
				 							.sku(stock.getSku())
				 							.quantity(stock.getQuantity())
				 							.build();
				 				})
				 				.collect(Collectors.toList());
		 }
		 
		 return products;
	}
	
	public Product getProductById(String productId) throws ProductOrchException {
		ProductDetails pd = productDetailsRepository.getProductById(productId);
		Stock stock = stockRepository.getStockById(productId);
		return Product.builder()
					.productId(pd.getProductId())
					.name(pd.getName())
					.description(pd.getDescription())
					.price(pd.getPrice())
					.sku(stock.getSku())
					.quantity(stock.getQuantity())
					.build();
	}

}
