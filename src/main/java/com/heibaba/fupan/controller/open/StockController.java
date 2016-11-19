package com.heibaba.fupan.controller.open;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.common.utils.DateUtil;
import com.heibaba.common.utils.RedisUtil;
import com.heibaba.fupan.dto.StockBaseInfoDto;
import com.heibaba.fupan.dto.StockTxInfoDto;
import com.heibaba.fupan.entity.rdb.StockBaseInfoEntity;
import com.heibaba.fupan.entity.rdb.StockTxInfoEntity;
import com.heibaba.fupan.service.StockBaseInfoService;
import com.heibaba.fupan.service.StockExtInfoService;
import com.heibaba.fupan.service.StockTxInfoService;

@RestController
public class StockController extends FupanOpenParentController {
	
	@Autowired
	private RedisUtil redis;
	@Autowired
	private StockBaseInfoService stockBaseInfoService;
	@Autowired
	private StockExtInfoService stockExtInfoService;
	@Autowired
	private StockTxInfoService stockTxInfoService;
	
	/**
	 * 按代码查询
	 * @return
	 */
	@RequestMapping(value = "/stock/{daima}", method = RequestMethod.GET, headers = "version=1.0.0")
	public StockBaseInfoEntity getStockInfo(@PathVariable(value = "daima") String daima) {
		
		return stockBaseInfoService.get(daima);
	}
	
	/**
	 * 按代码查询
	 * @return
	 */
	@RequestMapping(value = "/stock/base/{daima}", method = RequestMethod.GET, headers = "version=1.0.0")
	public StockBaseInfoDto getStockBaseInfo(@PathVariable(value = "daima") String daima) {
		
		StockBaseInfoEntity entity = stockBaseInfoService.get(daima);
		StockBaseInfoDto dto = new StockBaseInfoDto();
		if (entity != null) {
			BeanUtils.copyProperties(entity, dto);
		}
		
		return dto;
	}
	
	/**
	 * 按中文名查询
	 * @return
	 */
	@RequestMapping(value = "/stock/base/zhongwenming/{zhongwenming}", method = RequestMethod.GET, headers = "version=1.0.0")
	public StockBaseInfoDto getStockBaseInfoByZhongwenming(@PathVariable(value = "zhongwenming") String zhongwenming) {
		
		StockBaseInfoEntity entity = stockBaseInfoService.getByZhongwenming(zhongwenming);
		StockBaseInfoDto dto = new StockBaseInfoDto();
		if (entity != null) {
			BeanUtils.copyProperties(entity, dto);
		}
		
		return dto;
	}
	
	/**
	 * 按所属板块查询
	 * @return
	 */
	@RequestMapping(value = "/stock/bankuai", method = RequestMethod.GET, headers = "version=1.0.0")
	public List<StockBaseInfoEntity> getByBankuai(@RequestParam(value = "bankuai", required = true) String bankuai) {
		
		String[] bankuais = bankuai.trim().split(" ");
		return stockBaseInfoService.getByBankuai(bankuais);
	}
	
	/**
	 * 按描述关键字查询
	 * @return
	 */
	@RequestMapping(value = "/stock/keyword", method = RequestMethod.GET, headers = "version=1.0.0")
	public List<StockBaseInfoEntity> getByKeyword(@RequestParam(value = "keyword", required = true) String keyword) {
		
		String[] keywords = keyword.trim().split(" ");
		return stockBaseInfoService.getByKeyword(keywords);
	}
	
	/**
	 * 最近复牌股
	 * @return
	 */
	@RequestMapping(value = "/stock/fupai/{day}", method = RequestMethod.GET, headers = "version=1.0.0")
	public List<StockBaseInfoEntity> getByFupaiDay(@PathVariable(value = "day") int day) {
		
		return stockBaseInfoService.getByFupaiDay(day);
	}
	
	/**
	 * 最近大事提醒(daybefore天之前 至 dayafter天之后 区间内的提醒)
	 * @param daybefore 几天之前
	 * @param dayafter 几条之后
	 * @return
	 */
	@RequestMapping(value = "/stock/tixing/{daybefore}/{dayafter}", method = RequestMethod.GET, headers = "version=1.0.0")
	public List<StockTxInfoDto> getByTixingDay(@PathVariable(value = "daybefore") int daybefore, @PathVariable(value = "dayafter") int dayafter) {
		
		List<StockTxInfoDto> list = new ArrayList<StockTxInfoDto>();
		List<StockTxInfoEntity> listdb = stockTxInfoService.getByTixingDay(daybefore, dayafter);
		for (StockTxInfoEntity record : listdb) {
			StockTxInfoDto dto = new StockTxInfoDto();
			BeanUtils.copyProperties(record, dto);
			dto.setZhongwenming(String.valueOf(redis.get(record.getDaima())));
			list.add(dto);
		}
		
		return list;
	}
	
	/**
	 * 每日复盘
	 * @return
	 */
	@RequestMapping(value = "/stock/fupan/{date}", method = RequestMethod.GET, headers = "version=1.0.0")
	public List<StockBaseInfoEntity> getByFupanDate(@PathVariable(value = "date") String date) {
		
		return stockBaseInfoService.getByFupanDate(DateUtil.strToDate(date, "yyyy-MM-dd"));
	}
	
	/**
	 * 最近top次复盘日期列表
	 * @return
	 */
	@RequestMapping(value = "/stock/fupan/datelist/{top}", method = RequestMethod.GET, headers = "version=1.0.0")
	public List<String> getLatestFupanList(@PathVariable(value = "top") int top) {
		
		return stockExtInfoService.findLatestFupanDateList(top);
	}
	
}
