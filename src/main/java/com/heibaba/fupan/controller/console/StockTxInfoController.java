package com.heibaba.fupan.controller.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.fupan.entity.rdb.StockTxInfoEntity;
import com.heibaba.fupan.service.StockTxInfoService;

@RestController
public class StockTxInfoController extends FupanConsoleParentController {
	
	@Autowired
	private StockTxInfoService stockTxInfoService;
	
	@RequestMapping(value = "/stock/tixing/{id}", method = RequestMethod.GET, headers = "version=1.0.0")
	public StockTxInfoEntity update(@PathVariable(value = "id") int id) {
		return stockTxInfoService.get(id);
	}
	
	@RequestMapping(value = "/stock/tixing", method = RequestMethod.POST, headers = "version=1.0.0")
	public StockTxInfoEntity add(@RequestBody StockTxInfoEntity entity) {
		
		return stockTxInfoService.add(entity);
	}
	
	@RequestMapping(value = "/stock/tixing/{id}", method = RequestMethod.PUT, headers = "version=1.0.0")
	public StockTxInfoEntity update(@PathVariable(value = "id") int id, 
										@RequestBody StockTxInfoEntity entity) {
		entity.setId(id);
		return stockTxInfoService.update(entity);
	}
	
	@RequestMapping(value = "/stock/tixing/{id}", method = RequestMethod.DELETE, headers = "version=1.0.0")
	public void delete(@PathVariable(value = "id") int id) {
		
		stockTxInfoService.delete(id);
	}
	
}
