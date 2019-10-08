package com.souvik.productorchsvc.repository.action;

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

import com.souvik.productorchsvc.config.BaseConfiguration;
import com.souvik.productorchsvc.exception.ProductOrchException;
import com.souvik.productorchsvc.exception.RestClientException;
import com.souvik.productorchsvc.util.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestClientAction<T> {
	
	public T execute(BaseConfiguration configuration, RestTemplate template, HttpEntity<?> httpEntity, HttpMethod httpMethod,
			String path, String requestParams, String pathParam, ParameterizedTypeReference<T> responseType) throws ProductOrchException {
		log.info("Creating rest client call with configuration={}", configuration);
		ResponseEntity<T> responseEntity = null;
		try {
			StringBuilder file = new StringBuilder(configuration.getBasePath()).append(path);
			if (!StringUtils.isEmpty(requestParams)) {
				file.append(AppConstants.QUERY_PARAM_APPENDER).append(requestParams);
			}
			if (!StringUtils.isEmpty(pathParam)) {
				file.append(AppConstants.PATH_PARAM_APPENDER).append(pathParam);
			}
			URI uri = new URL(configuration.getSchema(), configuration.getHost(), configuration.getPort(), file.toString()).toURI();
			responseEntity = template.exchange(uri, httpMethod, httpEntity, responseType);
			if (Optional.ofNullable(responseEntity).isPresent() && 
					HttpStatus.OK.equals(responseEntity.getStatusCode())) {
				return responseEntity.getBody();
			} else {
				throw new RestClientException(AppConstants.REST_CLIENT_EXCEPTION + responseEntity.getStatusCode());
			}
		} catch (Exception e) {
			log.debug("Exception occured while invoking rest client : " + e);
			throw new ProductOrchException(e.getLocalizedMessage(), e);
		}
	}
	
	

}
