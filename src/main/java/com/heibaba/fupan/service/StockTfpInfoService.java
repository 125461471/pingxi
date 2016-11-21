package com.heibaba.fupan.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heibaba.fupan.entity.rdb.StockTfpInfoEntity;
import com.heibaba.fupan.repository.StockBaseInfoRepository;
import com.heibaba.fupan.repository.StockTfpInfoRepository;

@Service
@Transactional
public class StockTfpInfoService {
	
	@Autowired
	private StockTfpInfoRepository stockTfpInfoRepository;
	@Autowired
	private StockBaseInfoRepository stockBaseInfoRepository;

	public StockTfpInfoEntity get(int id) {

		return stockTfpInfoRepository.getOne(id);
	}
	
	public StockTfpInfoEntity add(StockTfpInfoEntity entity) {

		stockBaseInfoRepository.updateJiluDate(entity.getDaima(), new Date());
		return stockTfpInfoRepository.save(entity);
	}
	
	public StockTfpInfoEntity update(StockTfpInfoEntity entity) {

		return stockTfpInfoRepository.save(entity);
	}

	public void delete(int id) {

		stockTfpInfoRepository.delete(id);
	}
}
