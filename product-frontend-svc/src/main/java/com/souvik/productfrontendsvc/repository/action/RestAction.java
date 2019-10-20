package com.souvik.productfrontendsvc.repository.action;

import java.net.URI;
import java.net.URL;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.souvik.productfrontendsvc.config.BaseConfiguration;
import com.souvik.productfrontendsvc.exception.ProductException;
import com.souvik.productfrontendsvc.exception.RestException;
import com.souvik.productfrontendsvc.util.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestAction<T> {
	
	public T call(BaseConfiguration configuration, RestTemplate template, HttpEntity<?> httpEntity, HttpMethod httpMethod,
			String path, String requestParams, String pathParam, ParameterizedTypeReference<T> responseType) throws ProductException {
		log.info("Creating rest client call with configuration={}", configuration);
		ResponseEntity<T> responseEntity = null;
		URI uri = null;
		try {
			StringBuilder file = new StringBuilder(configuration.getBasePath()).append(path);
			if (!StringUtils.isEmpty(requestParams)) {
				file.append(AppConstants.QUERY_PARAM_APPENDER).append(requestParams);
			}
			if (!StringUtils.isEmpty(pathParam)) {
				file.append(AppConstants.PATH_PARAM_APPENDER).append(pathParam);
			}
			uri = new URL(configuration.getSchema(), configuration.getHost(), configuration.getPort(), file.toString()).toURI();
			responseEntity = template.exchange(uri, httpMethod, httpEntity, responseType);
			if (Optional.ofNullable(responseEntity).isPresent() && 
					HttpStatus.OK.equals(responseEntity.getStatusCode())) {
				return responseEntity.getBody();
			} else {
				throw new RestException(String.format(AppConstants.REST_EXCEPTION, uri, responseEntity.getStatusCode()));
			}
		} catch (Exception e) {
			log.debug("Exception occured while invoking rest client : " + e);
			throw new ProductException(e.getLocalizedMessage(), e);
		}
	}
	
	

}
