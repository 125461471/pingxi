package com.heibaba.usercenter.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heibaba.common.exception.BusinessException;
import com.heibaba.common.utils.DateUtil;
import com.heibaba.common.utils.MathUtil;
import com.heibaba.usercenter.dto.LatestRateOfReturnDto;
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
		AssetsEntity record = assetsRepository.save(entity);
		calculateAndSaveRateOfReturn(entity.getUserId(), entity.getAccountId(), entity.getAssetsDate());
		
		return record;
	}

	public AssetsEntity update(AssetsEntity entity) {

		AssetsEntity result = new AssetsEntity();
		AssetsEntity record = new AssetsEntity();
		record = assetsRepository.getOne(entity.getId());
		if (record != null) {
			if (record.getUserId() == entity.getUserId() && record.getAccountId() == entity.getAccountId()) {//权限校验
				if (record.getAssetsDate().compareTo(DateUtil.strToDate(DateUtil.dateToStr(entity.getAssetsDate(), "yyyy-MM-dd"), "yyyy-MM-dd")) == 0) {//若日期没改变，则直接更新
					result = assetsRepository.save(entity);
					calculateAndSaveRateOfReturn(entity.getUserId(), entity.getAccountId(), entity.getAssetsDate());
				} else {//若日期改变，则判断此账户是否已存在此资产日期的记录
					if (assetsRepository.findByUserIdAndAccountIdAndAssetsDate(entity.getUserId(), entity.getAccountId(), entity.getAssetsDate()) != null) {
						throw new BusinessException("已存在["+DateUtil.dateToStr(entity.getAssetsDate(), "yyyy-MM-dd")+"]的资产记录，请检查本次填写的日期是否正确。");
					} else {
						int compare = 0;
						if (record.getAssetsDate().compareTo(DateUtil.strToDate(DateUtil.dateToStr(entity.getAssetsDate(), "yyyy-MM-dd"), "yyyy-MM-dd")) > 0) {//修改的日期在原日期之前，即往前修改
							compare = 1;
						}
						result = assetsRepository.save(entity);//持久化后，【record = assetsRepository.getOne(entity.getId());】record变量中的实体也更新了！！！！！！！！
						if (compare > 0) {//修改的日期在原日期之前，即往前修改
							calculateAndSaveRateOfReturn(entity.getUserId(), entity.getAccountId(), entity.getAssetsDate());
						} else {//往后修改
							calculateAndSaveRateOfReturn(entity.getUserId(), entity.getAccountId(), record.getAssetsDate());
						}
					}
				}
			}
		}
		
		return result;
	}

//	public void delete(int id, int userId, int accountId) {
//
//		AssetsEntity record = new AssetsEntity();
//		record = assetsRepository.getOne(id);
//		if (record != null) {
//			if (record.getUserId() == userId && record.getAccountId() == accountId) {//权限校验
//				assetsRepository.delete(id);
//			}
//		}
//	}
	
	public Page<AssetsEntity> list(int userId, int accountId, Pageable pageable) {

		return assetsRepository.findAllByUserIdAndAccountId(userId, accountId, pageable);
	}
	
	public AssetsEntity getPreMonthAssets(int userId, int accountId) {

		//先查询上个月月末资产
		AssetsEntity entity = assetsRepository.findEndOfPreMonth(userId, accountId);
		if (entity == null) {//查询本月月初资产
			entity = assetsRepository.findBeginningOfThisMonth(userId, accountId);
		}
		
		return entity;
	}
	
	/**
	 * 获取最新收益率（非加权算法）
	 * 适合一年中资产无转入转出的情况
	 * @param userId
	 * @param accountId
	 * @return
	 */
	public LatestRateOfReturnDto getLatestRateOfReturn(int userId, int accountId) {

		LatestRateOfReturnDto rate = new LatestRateOfReturnDto();
		//查询上个月月末资产
		AssetsEntity preMonthAssets = getPreMonthAssets(userId, accountId);
		//查询本年年初资产
		AssetsEntity beginOfThisYearAssets = assetsRepository.findBeginningOfThisYear(userId, accountId);
		//查询最新资产
		AssetsEntity latestAssets = assetsRepository.findLatest(userId, accountId);
		
		rate.setRateOfMonthReturn(
				Math.round(
						((latestAssets.getAssets()-preMonthAssets.getAssets())/preMonthAssets.getAssets()) * 100 * 100
				) * 0.01
			 );
		
		rate.setRateOfYearReturn(
				Math.round(
						((latestAssets.getAssets()-beginOfThisYearAssets.getAssets())/beginOfThisYearAssets.getAssets()) * 100 * 100
				) * 0.01
			 );
		
		return rate;
	}
	
	/**
	 * 计算收益率（加权算法）
	 * 重新计算从startDate（含）开始，到现在的所有记录的收益率
	 * @param startDate
	 * @return 修改的记录条数
	 */
	private int calculateAndSaveRateOfReturn(int userId, int accountId, Date startDate) {
		
		int count = 0;
		//获取前一个交易日的资产记录
		AssetsEntity preDateAssets = assetsRepository.findPreDate(userId, accountId, DateUtil.strToDate(DateUtil.dateToStr(startDate, "yyyy-MM-dd"), "yyyy-MM-dd"));
		//获取startDate（含）开始，到现在的所有资产记录
		Date preDate = DateUtil.dateBefore(startDate, 1);
		List<AssetsEntity> list = assetsRepository.findAllByUserIdAndAccountIdAndAssetsDateAfterOrderByAssetsDateAsc(userId, accountId, preDate);
		double dayRate = 0;
		for (AssetsEntity record : list) {
			if (preDateAssets != null) {
				//计算日收益率
				dayRate = calculateOneTimeRate(preDateAssets.getAssets(), record.getAssets(), record.getTransfer());
//				dayRate = Math.round(
//								((record.getAssets()-record.getTransfer()-preDateAssets.getAssets())/preDateAssets.getAssets()) * 100 * 10000
//						  ) * 0.0001;
				record.setDayRate(dayRate);
				//计算月累计收益率
				if (DateUtil.sameMonth(record.getAssetsDate(), preDateAssets.getAssetsDate())) {
					record.setMonthRate(
								calculateAccumulationRate(preDateAssets.getMonthRate(), record.getDayRate())
//								Math.round(
//										((1+preDateAssets.getMonthRate()*0.01) * (1+record.getDayRate()*0.01) - 1) * 100 * 10000
//								) * 0.0001
							);
				} else {
					record.setMonthRate(dayRate);
				}
				//计算季度累计收益率
				if (DateUtil.sameQuarter(record.getAssetsDate(), preDateAssets.getAssetsDate())) {
					record.setQuarterRate(
								calculateAccumulationRate(preDateAssets.getQuarterRate(), record.getDayRate())
//								Math.round(
//										((1+preDateAssets.getQuarterRate()*0.01) * (1+record.getDayRate()*0.01) - 1) * 100 * 10000
//								) * 0.0001
							);
				} else {
					record.setQuarterRate(dayRate);
				}
				//计算年度累计收益率
				if (DateUtil.sameYear(record.getAssetsDate(), preDateAssets.getAssetsDate())) {
					record.setYearRate(
								calculateAccumulationRate(preDateAssets.getYearRate(), record.getDayRate())
//								Math.round(
//										((1+preDateAssets.getYearRate()*0.01) * (1+record.getDayRate()*0.01) - 1) * 100 * 10000
//								) * 0.0001
							);
				} else {
					record.setYearRate(dayRate);
				}
			} else {
				record.setDayRate(0);
				record.setMonthRate(0);
				record.setQuarterRate(0);
				record.setYearRate(0);
			}
			assetsRepository.save(record);
			preDateAssets = record;
			count++;
		}
		
		return count;
	}
	
	/**
	 * 计算一次性收益率
	 * 返回%前面的部分，并四舍五入保留4位小数，如33.1234%则返回33.1234
	 * @param bopAssets 期初资产
	 * @param eopAssets 期末资产
	 * @param transfer 转入转出（入+；出-）
	 */
	private double calculateOneTimeRate(double bopAssets, double eopAssets, double transfer) {
		
		BigDecimal bop = new BigDecimal(bopAssets);
		BigDecimal eop = new BigDecimal(eopAssets);
		BigDecimal tra = new BigDecimal(transfer);
		BigDecimal oneHundred = new BigDecimal(100);
		
		return MathUtil.roud(
								(
									(
										eop.subtract(tra).subtract(bop)
									).divide(bop, 10, BigDecimal.ROUND_HALF_UP)
								).multiply(oneHundred)
							, 4);
	}
	
	/**
	 * 计算累计收益率
	 * 返回%前面的部分，并四舍五入保留4位小数，如33.1234%则返回33.1234
	 * @param accumulationRate 上次累计收益率
	 * @param thisRate 本次收益率
	 */
	private double calculateAccumulationRate(double accumulationRate, double thisRate) {
		
//		record.setYearRate(
//				Math.round(
//						((1+preDateAssets.getYearRate()*0.01) * (1+record.getDayRate()*0.01) - 1) * 100 * 10000
//				) * 0.0001
//			);
		BigDecimal acc = new BigDecimal(accumulationRate);
		BigDecimal thi = new BigDecimal(thisRate);
		BigDecimal oneHundred = new BigDecimal(100);
		BigDecimal one = new BigDecimal(1);
		
		return MathUtil.roud(
								(
									(
										(one.add( acc.multiply(new BigDecimal(0.01)) ))
									).multiply(
										(one.add( thi.multiply(new BigDecimal(0.01)) ))
									).subtract(
										one
									)
								).multiply(oneHundred)
							, 4);
	}


}
