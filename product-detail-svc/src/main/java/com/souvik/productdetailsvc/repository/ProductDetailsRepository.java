package com.souvik.productdetailsvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souvik.productdetailsvc.bean.ProductDetails;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, String> {

}
