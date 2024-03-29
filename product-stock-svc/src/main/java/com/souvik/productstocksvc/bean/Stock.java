package com.souvik.productstocksvc.bean;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
	
	@Id
	private String productId;
	private String sku;
	private Long quantity;
	private BigDecimal price;
}
