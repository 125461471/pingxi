package com.heibaba;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.heibaba.common.utils.RedisUtil;
import com.heibaba.fupan.dto.StockRedisDto;
import com.heibaba.fupan.entity.rdb.StockBaseInfoEntity;
import com.heibaba.fupan.repository.StockBaseInfoRepository;

/**
 * 程序启动时加载项
 * @author shenhl
 *
 */
@Component
public class StartupRunner implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

	@Autowired
	private RedisUtil redis;
	@Autowired
	private StockBaseInfoRepository stockBaseInfoRepository;
	
	/**
	 * 加载入口方法
	 */
	@Override
    public void run(String... args) throws Exception {
		
		//股票信息写入缓存[代码:中文名]、[STOCK_LIST:拼音,代码,中文名]
		List<StockBaseInfoEntity> list = stockBaseInfoRepository.findAll();
		List<StockRedisDto> stock_list = new ArrayList<StockRedisDto>();
		for (StockBaseInfoEntity entity : list) {
			redis.set(entity.getDaima(), entity.getZhongwenming());
			stock_list.add(new StockRedisDto(entity.getDaima(), entity.getPinyin(), entity.getZhongwenming()));
		}
		redis.set("STOCK_LIST", stock_list);
		logger.info("加载项加载成功 ......");
    }

}
