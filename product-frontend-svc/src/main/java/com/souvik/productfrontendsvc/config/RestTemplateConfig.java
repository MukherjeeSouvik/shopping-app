package com.souvik.productfrontendsvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.souvik.productfrontendsvc.util.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RestTemplateConfig {
	
	@Autowired
	private ProductApiConfiguration prouctApiConfiguration;
	
	@Bean(name = AppConstants.PRODUCT_TEMPLATE)
	public RestTemplate stockRestTemplate() {
		log.info("Creating Stock Api Template with parameters={}", prouctApiConfiguration);
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(prouctApiConfiguration.getConnectionTimeout());
		requestFactory.setReadTimeout(prouctApiConfiguration.getReadTimeout());
		return new RestTemplate(requestFactory);
	}
	
	
	
	

}
