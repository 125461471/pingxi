package com.heibaba.usercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heibaba.usercenter.entity.rdb.AssetsRateOfReturnEntity;
import com.heibaba.usercenter.repository.AssetsRateOfReturnRepository;

@Service
@Transactional
public class AssetsRateOfReturnService {
	
	@Autowired
	private AssetsRateOfReturnRepository assetsRateOfReturnRepository;
	
	
	/**
	 * 获取收益率
	 * @param userId
	 * @param accountId
	 * @return
	 */
	public AssetsRateOfReturnEntity getRateOfReturn(int userId, int accountId) {

		AssetsRateOfReturnEntity rate = new AssetsRateOfReturnEntity();
		
		return rate;
	}

}
