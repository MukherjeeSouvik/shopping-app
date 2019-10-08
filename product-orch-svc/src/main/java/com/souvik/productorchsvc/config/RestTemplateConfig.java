package com.souvik.productorchsvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.souvik.productorchsvc.util.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RestTemplateConfig {
	
	@Autowired
	private StockApiConfiguration stockApiConfiguration;
	
	@Bean(name = AppConstants.STOCK_TEMPLATE)
	public RestTemplate stockRestTemplate() {
		log.info("Creating Stock Api Template with parameters={}", stockApiConfiguration);
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(stockApiConfiguration.getConnectionTimeout());
		requestFactory.setReadTimeout(stockApiConfiguration.getReadTimeout());
		return new RestTemplate(requestFactory);
	}
	
	
	

}
