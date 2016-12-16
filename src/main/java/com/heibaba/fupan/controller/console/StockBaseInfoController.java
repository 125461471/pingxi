package com.heibaba.fupan.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.fupan.entity.rdb.StockBaseInfoEntity;
import com.heibaba.fupan.service.StockBaseInfoService;

@RestController
public class StockBaseInfoController extends FupanConsoleParentController {
	
	@Autowired
	private StockBaseInfoService stockBaseInfoService;  
	
	@RequestMapping(value = "/stock/base", method = RequestMethod.POST, headers = "version=1.0.0")
	public StockBaseInfoEntity add(@RequestBody StockBaseInfoEntity entity) {
		
		return stockBaseInfoService.add(entity);
	}
	
	@RequestMapping(value = "/stock/base/{daima}", method = RequestMethod.PUT, headers = "version=1.0.0")
	public StockBaseInfoEntity update(@PathVariable(value = "daima") String daima, 
										@RequestBody StockBaseInfoEntity entity) {
		entity.setDaima(daima);
		return stockBaseInfoService.update(entity);
	}
	
	@RequestMapping(value = "/stock/base/{daima}", method = RequestMethod.DELETE, headers = "version=1.0.0")
	public void delete(@PathVariable(value = "daima") String daima) {
		
		stockBaseInfoService.delete(daima);
	}
	
	@RequestMapping(value = "/stock/base", method = RequestMethod.GET, headers = "version=1.0.0")
	public List<StockBaseInfoEntity> getAll() {
		
		return stockBaseInfoService.getAll();
	}
	
}
