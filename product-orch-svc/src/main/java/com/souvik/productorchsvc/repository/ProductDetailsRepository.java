package com.souvik.productorchsvc.repository;

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

import com.souvik.productorchsvc.bean.ProductDetails;
import com.souvik.productorchsvc.bean.Stock;
import com.souvik.productorchsvc.config.ProductApiConfiguration;
import com.souvik.productorchsvc.exception.ProductOrchException;
import com.souvik.productorchsvc.repository.action.RestAction;
import com.souvik.productorchsvc.util.AppConstants;

@Component
public class ProductDetailsRepository {
	
	@Autowired
	private ProductApiConfiguration configuration;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RestAction restAction;
	
	@Autowired
	@Qualifier(AppConstants.PD_TEMPLATE)
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
	public List<ProductDetails> getProductDetails() throws ProductOrchException {
		return (List<ProductDetails>) restAction
					.call(configuration, template, httpEntity, HttpMethod.GET, 
							configuration.getPath(), null, null,
							new ParameterizedTypeReference<List<ProductDetails>>() {});
	}
	public Map<String, ProductDetails> getProductDetailsMap() throws ProductOrchException {
		return getProductDetails()
				.stream()
				.collect(Collectors.toMap(ProductDetails::getProductId, Function.identity()));
	}
	
	@SuppressWarnings("unchecked")
	public ProductDetails getProductById(String productId) throws ProductOrchException {
		return (ProductDetails) restAction
					.call(configuration, template, httpEntity, HttpMethod.GET, 
							configuration.getPath(), null, productId,
							new ParameterizedTypeReference<ProductDetails>() {});
	}

}
