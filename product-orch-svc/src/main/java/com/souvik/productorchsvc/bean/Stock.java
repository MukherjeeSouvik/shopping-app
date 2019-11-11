package com.souvik.productorchsvc.bean;

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
public class Stock {
	private String productId;
	private String sku;
	private Long quantity;
	private BigDecimal price;
}
