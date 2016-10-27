package com.heibaba.fupan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.heibaba.fupan.entity.rdb.StockExtInfoEntity;

public interface StockExtInfoRepository extends JpaRepository<StockExtInfoEntity, Integer> {
	
	List<StockExtInfoEntity> findAll(Specification<StockExtInfoEntity> spec);
	
	List<StockExtInfoEntity> findByFashengDate(Date fashengDate);
	
	/**
	 * 最近top次复盘日期列表
	 * @param fashengDate
	 * @return
	 */
	@Query(value="select DISTINCT(fasheng_date) from stock_ext_info order by fasheng_date desc limit ?1", nativeQuery=true)
	List<Date> findLatestFupanList(int toop);
}
