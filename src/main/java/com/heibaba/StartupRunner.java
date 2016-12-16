package com.heibaba;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.heibaba.fupan.dto.AutoCompleteDto;
import com.heibaba.fupan.dto.StockChiefInfoDto;
import com.heibaba.fupan.entity.rdb.StockBaseInfoEntity;
import com.heibaba.fupan.repository.AutoCompleteRepository;
import com.heibaba.fupan.repository.StockBaseInfoRepository;
import com.heibaba.fupan.repository.StockChiefInfoRepository;

/**
 * 程序启动时加载项
 * @author shenhl
 *
 */
@Component
public class StartupRunner implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

	@Autowired
	private StockBaseInfoRepository stockBaseInfoRepository;
	@Autowired
	private StockChiefInfoRepository stockChiefInfoRepository;
	@Autowired
	private AutoCompleteRepository autoCompleteRepository;

	/**
	 * 加载入口方法
	 */
	@Override
    public void run(String... args) throws Exception {
		
		logger.info("加载项加载开始 ......");
		//股票信息写入缓存[代码:拼音,代码,中文名][代码,代码 拼音 中文名]
		List<StockBaseInfoEntity> list = stockBaseInfoRepository.findAll();
		for (StockBaseInfoEntity entity : list) {
			stockChiefInfoRepository.save(new StockChiefInfoDto(entity.getDaima(), entity.getPinyin(), entity.getZhongwenming()));
			autoCompleteRepository.save(new AutoCompleteDto(entity.getDaima(), entity.getDaima()+" "+entity.getPinyin()+" "+entity.getZhongwenming()));
//			Global.stockMap.put(entity.getDaima(), new StockChiefInfoDto(entity.getDaima(), entity.getPinyin(), entity.getZhongwenming()));
//			Global.stockList.add(new AutoCompleteDto(entity.getDaima(), entity.getDaima()+" "+entity.getPinyin()+" "+entity.getZhongwenming()));
		}
		logger.info("加载项加载成功 ......");
    }

}
