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

import com.souvik.productorchsvc.bean.Stock;
import com.souvik.productorchsvc.config.StockApiConfiguration;
import com.souvik.productorchsvc.exception.ProductOrchException;
import com.souvik.productorchsvc.repository.action.RestAction;
import com.souvik.productorchsvc.util.AppConstants;

@Component
public class StockRepository {
	
	@Autowired
	private StockApiConfiguration configuration;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RestAction restAction;
	
	@Autowired
	@Qualifier(AppConstants.STOCK_TEMPLATE)
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
	public List<Stock> getStocks() throws ProductOrchException {
		return (List<Stock>) restAction
					.call(configuration, template, httpEntity, HttpMethod.GET, 
							configuration.getPath(), null, null,
							new ParameterizedTypeReference<List<Stock>>() {});
	}
	
	public Map<String, Stock> getStockMap() throws ProductOrchException {
		return getStocks()
				.stream()
				.collect(Collectors.toMap(Stock::getProductId, Function.identity()));
	}
	
	@SuppressWarnings("unchecked")
	public Stock getStockById(String productId) throws ProductOrchException {
		return (Stock) restAction
					.call(configuration, template, httpEntity, HttpMethod.GET, 
							configuration.getPath(), null, productId,
							new ParameterizedTypeReference<Stock>() {});
	}
	
	

}
