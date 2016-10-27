package com.heibaba.fupan.controller.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.fupan.entity.rdb.StockTfpInfoEntity;
import com.heibaba.fupan.service.StockTfpInfoService;

@RestController
public class StockTfpInfoController extends FupanConsoleParentController {
	
	@Autowired
	private StockTfpInfoService stockTfpInfoService;

	@RequestMapping(value = "/stock/tingfupai/{id}", method = RequestMethod.GET, headers = "version=1.0.0")
	public StockTfpInfoEntity get(@PathVariable(value = "id") int id) {
		return stockTfpInfoService.get(id);
	}
	
	@RequestMapping(value = "/stock/tingfupai", method = RequestMethod.POST, headers = "version=1.0.0")
	public StockTfpInfoEntity add(@RequestBody StockTfpInfoEntity entity) {
		
		return stockTfpInfoService.add(entity);
	}
	
	@RequestMapping(value = "/stock/tingfupai/{id}", method = RequestMethod.PUT, headers = "version=1.0.0")
	public StockTfpInfoEntity update(@PathVariable(value = "id") int id, 
										@RequestBody StockTfpInfoEntity entity) {
		entity.setId(id);
		return stockTfpInfoService.update(entity);
	}
	
	@RequestMapping(value = "/stock/tingfupai/{id}", method = RequestMethod.DELETE, headers = "version=1.0.0")
	public void delete(@PathVariable(value = "id") int id) {
		
		stockTfpInfoService.delete(id);
	}
	
}
