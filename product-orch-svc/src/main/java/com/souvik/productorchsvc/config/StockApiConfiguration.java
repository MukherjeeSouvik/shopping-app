package com.souvik.productorchsvc.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "services.stockapi")
public class StockApiConfiguration implements BaseConfiguration {
	
	private String schema;
	private String host;
	private int port;
	private int connectionTimeout;
	private int readTimeout;
	private String basePath;
	private Map<String, String> httpHeaders;
	private String path;

}
