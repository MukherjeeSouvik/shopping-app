package com.souvik.productdetailsvc.bean;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetails {
	
	private String productId;
	private String name;
	private String description;
	private BigDecimal price;
}
