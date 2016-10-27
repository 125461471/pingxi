package com.heibaba.fupan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.heibaba.fupan.entity.rdb.StockBaseInfoEntity;

public interface StockBaseInfoRepository extends JpaRepository<StockBaseInfoEntity, String> {
	
	List<StockBaseInfoEntity> findAll(Specification<StockBaseInfoEntity> spec);
	
	List<StockBaseInfoEntity> findAllByDaimaInOrderByJiluDateDesc(List<String> daimaList);
	
	@Modifying
	@Query(value="update StockBaseInfoEntity a set a.jiluDate=:date where a.daima =:daima")
	public int updateJiluDate(@Param("daima") String daima, @Param("date") Date date);

}
