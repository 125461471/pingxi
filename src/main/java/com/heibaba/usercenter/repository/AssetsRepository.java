package com.heibaba.usercenter.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.heibaba.usercenter.entity.rdb.AssetsEntity;

public interface AssetsRepository extends JpaRepository<AssetsEntity, Integer> {
	
	//查找此账户是否已存在此资产日期的记录
	//业务上用于限制一个账户一个资产日期的记录只能有一条
	AssetsEntity findByUserIdAndAccountIdAndAssetsDate(int userId, int accountId, Date assetsDate);

	/**
	 * 查询指定月的月初资产
	 */
	@Query(value="select a.* from assets a where a.user_id = :userId and a.account_id = :accountId and DATE_FORMAT(a.assets_date, '%Y%m') = DATE_FORMAT(:assetsDate, '%Y%m') order by a.assets_date asc limit 1", nativeQuery=true)
	AssetsEntity findBeginningOfMonth(@Param("userId") int userId, @Param("accountId") int accountId, @Param("assetsDate") Date assetsDate);
	
	/**
	 * 查询指定月的月末资产
	 */
	@Query(value="select a.* from assets a where a.user_id = :userId and a.account_id = :accountId and DATE_FORMAT(a.assets_date, '%Y%m') = DATE_FORMAT(:assetsDate, '%Y%m') order by a.assets_date desc limit 1", nativeQuery=true)
	AssetsEntity findEndOfMonth(@Param("userId") int userId, @Param("accountId") int accountId, @Param("assetsDate") Date assetsDate);
	
	/**
	 * 查询本月初资产
	 */
	@Query(value="select a.* from assets a where a.user_id = :userId and a.account_id = :accountId and DATE_FORMAT(a.assets_date, '%Y%m') = DATE_FORMAT(CURDATE(), '%Y%m') order by a.assets_date asc limit 1", nativeQuery=true)
	AssetsEntity findBeginningOfThisMonth(@Param("userId") int userId, @Param("accountId") int accountId);

	/**
	 * 查询上个月月末资产
	 */
	@Query(value="select a.* from assets a where a.user_id = :userId and a.account_id = :accountId and PERIOD_DIFF(DATE_FORMAT(NOW() , '%Y%m'), DATE_FORMAT(a.assets_date, '%Y%m')) = 1 order by a.assets_date desc limit 1", nativeQuery=true)
	AssetsEntity findEndOfPreMonth(@Param("userId") int userId, @Param("accountId") int accountId);

	/**
	 * 查询本年年初资产
	 */
	@Query(value="select a.* from assets a where a.user_id = :userId and a.account_id = :accountId and DATE_FORMAT(a.assets_date, '%Y') = DATE_FORMAT(CURDATE(), '%Y') order by a.assets_date asc limit 1", nativeQuery=true)
	AssetsEntity findBeginningOfThisYear(@Param("userId") int userId, @Param("accountId") int accountId);

	/**
	 * 查询最新资产
	 */
	@Query(value="select a.* from assets a where a.user_id = :userId and a.account_id = :accountId order by a.assets_date desc limit 1", nativeQuery=true)
	AssetsEntity findLatest(@Param("userId") int userId, @Param("accountId") int accountId);

	/**
	 * 查询指定日期前一个交易日的资产
	 */
	@Query(value="select a.* from assets a where a.user_id = :userId and a.account_id = :accountId and a.assets_date < :assetsDate order by a.assets_date desc limit 1", nativeQuery=true)
	AssetsEntity findPreDate(@Param("userId") int userId, @Param("accountId") int accountId, @Param("assetsDate") Date assetsDate);

	Page<AssetsEntity> findAllByUserIdAndAccountId(int userId, int accountId, Pageable pageable);
	
	//生成收益曲线用
	List<AssetsEntity> findAllByUserIdAndAccountIdAndAssetsDateAfterOrderByAssetsDateAsc(int userId, int accountId, Date startDate);
	
	//生成收益曲线用
	List<AssetsEntity> findAllByUserIdAndAccountIdAndAssetsDateBetweenOrderByAssetsDateAsc(int userId, int accountId, Date startDate, Date endDate);
	
}
