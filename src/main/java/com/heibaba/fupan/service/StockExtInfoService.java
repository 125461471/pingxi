package com.heibaba.fupan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heibaba.common.utils.DateUtil;
import com.heibaba.fupan.entity.rdb.StockExtInfoEntity;
import com.heibaba.fupan.repository.StockBaseInfoRepository;
import com.heibaba.fupan.repository.StockExtInfoRepository;

@Service
@Transactional
public class StockExtInfoService {
	
	@Autowired
	private StockExtInfoRepository stockExtInfoRepository;
	@Autowired
	private StockBaseInfoRepository stockBaseInfoRepository;
	
	public StockExtInfoEntity get(int id) {

		return stockExtInfoRepository.getOne(id);
	}

	public StockExtInfoEntity add(StockExtInfoEntity entity) {
		
		stockBaseInfoRepository.updateJiluDate(entity.getDaima(), new Date());
		return stockExtInfoRepository.save(entity);
	}

	public StockExtInfoEntity update(StockExtInfoEntity entity) {

		return stockExtInfoRepository.save(entity);
	}

	public void delete(int id) {

		stockExtInfoRepository.delete(id);
	}
	
	/**
	 * 最近top次复盘日期列表
	 * @param id
	 * @return
	 */
	public List<String> findLatestFupanList(int top) {

		List<String> list_result = new ArrayList<String>();
		List<Date> list = stockExtInfoRepository.findLatestFupanList(top);
		for (Date entity : list) {
			list_result.add(DateUtil.dateToStr(entity, "yyyy-MM-dd"));
		}
		
		return list_result;
	}
}
