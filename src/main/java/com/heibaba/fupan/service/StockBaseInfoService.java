package com.heibaba.fupan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heibaba.common.Global;
import com.heibaba.common.utils.DateUtil;
import com.heibaba.fupan.dto.AutoCompleteDto;
import com.heibaba.fupan.dto.StockCacheDto;
import com.heibaba.fupan.entity.rdb.StockBaseInfoEntity;
import com.heibaba.fupan.entity.rdb.StockExtInfoEntity;
import com.heibaba.fupan.entity.rdb.StockTfpInfoEntity;
import com.heibaba.fupan.repository.StockBaseInfoRepository;
import com.heibaba.fupan.repository.StockExtInfoRepository;
import com.heibaba.fupan.repository.StockTfpInfoRepository;

@Service
@Transactional
public class StockBaseInfoService {
	
	@Autowired
	private StockBaseInfoRepository stockBaseInfoRepository;
	@Autowired
	private StockExtInfoRepository stockExtInfoRepository;
	@Autowired
	private StockTfpInfoRepository stockTfpInfoRepository;
	
	public StockBaseInfoEntity save(StockBaseInfoEntity entity) {

		StockBaseInfoEntity new_entity = stockBaseInfoRepository.save(entity);
		Global.stockMap.put(entity.getDaima(), new StockCacheDto(entity.getDaima(), entity.getPinyin(), entity.getZhongwenming()));
		for (AutoCompleteDto stock:Global.stockList) {
			if (entity.getDaima().equals(stock.getValue())) {
				Global.stockList.remove(stock);
				break;
			}
		}
		Global.stockList.push(new AutoCompleteDto(entity.getDaima(), entity.getDaima()+" "+entity.getPinyin()+" "+entity.getZhongwenming()));
		
		return new_entity;
	}

	public void delete(String daima) {

		stockBaseInfoRepository.delete(daima);
		Global.stockMap.remove(daima);
		for (AutoCompleteDto stock:Global.stockList) {
			if (daima.equals(stock.getValue())) {
				Global.stockList.remove(stock);
				break;
			}
		}
	}
	
	public StockBaseInfoEntity get(String daima) {

		return stockBaseInfoRepository.findOne(daima);
	}
	
	public List<StockBaseInfoEntity> getAll() {

		return stockBaseInfoRepository.findAll(new Sort("daima asc"));
	}

	/**
	 * 按中文名查询
	 * @return
	 */
	public StockBaseInfoEntity getByZhongwenming(String zhongwenming) {

		return stockBaseInfoRepository.findOneByZhongwenming(zhongwenming);
	}
	
	/**
	 * 按所属板块查询
	 * @return
	 */
	public List<StockBaseInfoEntity> getByBankuai(String... bankuais) {

		List<StockBaseInfoEntity> list = stockBaseInfoRepository.findAll(new Specification<StockBaseInfoEntity>() {
	        @Override
	        public Predicate toPredicate(Root<StockBaseInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            for (String bankuai : bankuais) {
	            	expressions.add(cb.like(root.<String>get("bankuai"), "%"+bankuai+"%"));
	            }
	            query.orderBy(cb.desc(root.<Date>get("jiluDate")));
	            return predicate;
	        }
	    });
	    
	    return list;
	}
	
	/**
	 * 按描述关键字查询
	 * @return
	 */
	public List<StockBaseInfoEntity> getByKeyword(String... keywords) {

		List<StockExtInfoEntity> list = stockExtInfoRepository.findAll(new Specification<StockExtInfoEntity>() {
	        @Override
	        public Predicate toPredicate(Root<StockExtInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	            Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            for (String keyword : keywords) {
	            	expressions.add(cb.like(root.<String>get("miaoshu"), "%"+keyword+"%"));
	            }
	            return predicate;
	        }
	    });
		
		Set<String> daimaList = new HashSet<String>();
		for (StockExtInfoEntity entity : list) {
			if (!daimaList.contains(entity)) {
				daimaList.add(entity.getDaima());
			}
		}
//		List<String> daimaList = new ArrayList<String>();
//		for (StockExtInfoEntity entity : list) {
//			if (!daimaList.contains(entity)) {
//				daimaList.add(entity.getDaima());
//			}
//		}

		return stockBaseInfoRepository.findAllByDaimaInOrderByJiluDateDesc(new ArrayList<String>(daimaList));
	}
	
	/**
	 * 最近复牌股
	 * @param daima
	 * @return
	 */
	public List<StockBaseInfoEntity> getByFupaiDay(int day) {

		List<StockTfpInfoEntity> list = stockTfpInfoRepository.findAllByFupaiDateAfter(DateUtil.dateBefore(day));
		List<String> daimaList = new ArrayList<String>();
		for (StockTfpInfoEntity entity : list) {
			daimaList.add(entity.getDaima());
		}
		
		return stockBaseInfoRepository.findAllByDaimaInOrderByJiluDateDesc(daimaList);
	}
	
	/**
	 * 每日复盘
	 * @return
	 */
	public List<StockBaseInfoEntity> getByFupanDate(Date date) {

		List<StockExtInfoEntity> list = stockExtInfoRepository.findByFashengDate(date);
		List<String> daimaList = new ArrayList<String>();
		for (StockExtInfoEntity entity : list) {
			daimaList.add(entity.getDaima());
		}
		
		return stockBaseInfoRepository.findAllByDaimaInOrderByJiluDateDesc(daimaList);
	}
	
}
