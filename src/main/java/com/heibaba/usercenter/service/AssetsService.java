package com.heibaba.usercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heibaba.common.exception.BusinessException;
import com.heibaba.common.utils.DateUtil;
import com.heibaba.usercenter.entity.rdb.AssetsEntity;
import com.heibaba.usercenter.repository.AssetsRepository;

@Service
@Transactional
public class AssetsService {
	
	@Autowired
	private AssetsRepository assetsRepository;
	
	public AssetsEntity get(int id, int userId, int accountId) {

		AssetsEntity record = new AssetsEntity();
		record = assetsRepository.getOne(id);
		if (record.getUserId() == userId && record.getAccountId() == accountId) {//权限校验
			return record;
		}
		
		return new AssetsEntity();
	}

	public AssetsEntity add(AssetsEntity entity) {
		
		//判断此账户是否已存在此资产日期的记录
		if (assetsRepository.findByUserIdAndAccountIdAndAssetsDate(entity.getUserId(), entity.getAccountId(), entity.getAssetsDate()) != null) {
			throw new BusinessException("已存在["+DateUtil.dateToStr(entity.getAssetsDate(), "yyyy-MM-dd")+"]的资产记录，请检查本次填写的日期是否正确。");
		}
		return assetsRepository.save(entity);
	}

	public AssetsEntity update(AssetsEntity entity) {

		AssetsEntity result = new AssetsEntity();
		AssetsEntity record = new AssetsEntity();
		record = assetsRepository.getOne(entity.getId());
		if (record != null) {
			if (record.getUserId() == entity.getUserId() && record.getAccountId() == entity.getAccountId()) {//权限校验
				if (record.getAssetsDate().compareTo(DateUtil.strToDate(DateUtil.dateToStr(entity.getAssetsDate(), "yyyy-MM-dd"), "yyyy-MM-dd")) == 0) {//若日期没改变，则直接更新
					result = assetsRepository.save(entity);
				} else {//若日期改变，则判断此账户是否已存在此资产日期的记录
					if (assetsRepository.findByUserIdAndAccountIdAndAssetsDate(entity.getUserId(), entity.getAccountId(), entity.getAssetsDate()) != null) {
						throw new BusinessException("已存在["+DateUtil.dateToStr(entity.getAssetsDate(), "yyyy-MM-dd")+"]的资产记录，请检查本次填写的日期是否正确。");
					} else {
						result = assetsRepository.save(entity);
					}
				}
			}
		}
		
		return result;
	}

	public void delete(int id, int userId, int accountId) {

		AssetsEntity record = new AssetsEntity();
		record = assetsRepository.getOne(id);
		if (record != null) {
			if (record.getUserId() == userId && record.getAccountId() == accountId) {//权限校验
				assetsRepository.delete(id);
			}
		}
	}
	
	public Page<AssetsEntity> list(int userId, int accountId, Pageable pageable) {

		return assetsRepository.findAllByUserIdAndAccountId(userId, accountId, pageable);
	}

}
