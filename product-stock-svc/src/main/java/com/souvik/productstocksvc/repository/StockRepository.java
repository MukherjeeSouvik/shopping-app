package com.souvik.productstocksvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souvik.productstocksvc.bean.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {

}
