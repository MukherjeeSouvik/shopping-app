package com.souvik.productfrontendsvc.config;

import java.util.Map;

public interface BaseConfiguration {
	
	public String getSchema();
	public String getHost();
	public int getPort();
	public int getConnectionTimeout();
	public int getReadTimeout();
	public String getBasePath();
	public Map<String, String> getHttpHeaders();

}
