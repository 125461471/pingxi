package com.heibaba.fupan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heibaba.fupan.entity.rdb.StockTfpInfoEntity;

public interface StockTfpInfoRepository extends JpaRepository<StockTfpInfoEntity, Integer> {
	
	List<StockTfpInfoEntity> findAllByFupaiDateAfter(Date date);
}
