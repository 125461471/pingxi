package com.heibaba.usercenter.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.heibaba.usercenter.entity.rdb.AssetsEntity;

public interface AssetsRepository extends JpaRepository<AssetsEntity, Integer> {
	
	//查找此账户是否已存在此资产日期的记录
	//业务上用于限制一个账户一个资产日期的记录只能有一条
	AssetsEntity findByUserIdAndAccountIdAndAssetsDate(int userId, int accountId, Date assetsDate);
	
	Page<AssetsEntity> findAllByUserIdAndAccountId(int userId, int accountId, Pageable pageable);
	
	//生成收益曲线用
	List<AssetsEntity> findAllByUserIdAndAccountIdAndAssetsDateAfterOrderByAssetsDateAsc(int userId, int accountId, Date startDate);
	
	//生成收益曲线用
	List<AssetsEntity> findAllByUserIdAndAccountIdAndAssetsDateBetweenOrderByAssetsDateAsc(int userId, int accountId, Date startDate, Date endDate);
	
}
