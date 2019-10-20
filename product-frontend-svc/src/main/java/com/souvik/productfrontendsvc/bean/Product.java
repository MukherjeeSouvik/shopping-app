package com.souvik.productfrontendsvc.bean;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	private String productId;
	private String sku;
	private Long quantity;
	
	private String name;
	private String description;
	private BigDecimal price;

}
