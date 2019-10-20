package com.souvik.productfrontendsvc.repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.souvik.productfrontendsvc.bean.Product;
import com.souvik.productfrontendsvc.config.ProductApiConfiguration;
import com.souvik.productfrontendsvc.exception.ProductException;
import com.souvik.productfrontendsvc.repository.action.RestAction;
import com.souvik.productfrontendsvc.util.AppConstants;

@Component
public class ProductRepository {
	
	@Autowired
	private ProductApiConfiguration configuration;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RestAction restAction;
	
	@Autowired
	@Qualifier(AppConstants.PRODUCT_TEMPLATE)
	private RestTemplate template;
	
	private HttpEntity<?> httpEntity;
	private HttpHeaders httpHeaders;
	
	@PostConstruct
	void init() {
		httpHeaders = new HttpHeaders();
		if (null != configuration.getHttpHeaders()) {
			configuration.getHttpHeaders()
				.forEach((key, value) -> httpHeaders.add(key, value));
		}
		
		httpEntity = new HttpEntity<>(httpHeaders);
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProduct() throws ProductException {
		return (List<Product>) restAction
					.call(configuration, template, httpEntity, HttpMethod.GET, 
							configuration.getPath(), null, null,
							new ParameterizedTypeReference<List<Product>>() {});
	}
	public Map<String, Product> getProductMap() throws ProductException {
		return getProduct()
				.stream()
				.collect(Collectors.toMap(Product::getProductId, Function.identity()));
	}
	
	@SuppressWarnings("unchecked")
	public Product getProductById(String productId) throws ProductException {
		return (Product) restAction
					.call(configuration, template, httpEntity, HttpMethod.GET, 
							configuration.getPath(), null, productId,
							new ParameterizedTypeReference<Product>() {});
	}

}
