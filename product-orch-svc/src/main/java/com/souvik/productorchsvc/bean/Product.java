package com.souvik.productorchsvc.bean;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {
	
	private String productId;
	private String sku;
	private Long quantity;
	
	private String name;
	private String description;
	private BigDecimal price;

}
