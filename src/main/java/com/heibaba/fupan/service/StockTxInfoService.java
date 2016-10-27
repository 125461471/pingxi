package com.heibaba.fupan.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heibaba.common.utils.DateUtil;
import com.heibaba.fupan.entity.rdb.StockTxInfoEntity;
import com.heibaba.fupan.repository.StockBaseInfoRepository;
import com.heibaba.fupan.repository.StockTxInfoRepository;

@Service
@Transactional
public class StockTxInfoService {
	
	@Autowired
	private StockTxInfoRepository stockTxInfoRepository;
	@Autowired
	private StockBaseInfoRepository stockBaseInfoRepository;
	
	public StockTxInfoEntity get(int id) {

		return stockTxInfoRepository.getOne(id);
	}

	public StockTxInfoEntity add(StockTxInfoEntity entity) {

		stockBaseInfoRepository.updateJiluDate(entity.getDaima(), new Date());
		return stockTxInfoRepository.save(entity);
	}

	public StockTxInfoEntity update(StockTxInfoEntity entity) {

		return stockTxInfoRepository.save(entity);
	}

	public void delete(int id) {

		stockTxInfoRepository.delete(id);
	}
	
	/**
	 * 最近大事提醒(daybefore天之前 至 dayafter天之后 区间内的提醒)
	 * @param daybefore 几天之前
	 * @param dayafter 几条之后
	 * @return
	 */
	public List<StockTxInfoEntity> getByTixingDay(int daybefore, int dayafter) {

		return stockTxInfoRepository.findAllByTixingDateAfterAndTixingDateBeforeOrderByTixingDateAsc(DateUtil.dateBefore(daybefore), DateUtil.dateAfter(dayafter));
	}
}
