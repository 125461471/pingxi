package com.heibaba.fupan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heibaba.fupan.entity.rdb.StockTxInfoEntity;

public interface StockTxInfoRepository extends JpaRepository<StockTxInfoEntity, Integer> {
	
	List<StockTxInfoEntity> findAllByTixingDateAfterAndTixingDateBeforeOrderByTixingDateAsc(Date beginDate, Date endDate);
}
