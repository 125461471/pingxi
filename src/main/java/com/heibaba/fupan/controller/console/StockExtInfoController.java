package com.heibaba.fupan.controller.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.fupan.entity.rdb.StockExtInfoEntity;
import com.heibaba.fupan.service.StockExtInfoService;

@RestController
public class StockExtInfoController extends FupanConsoleParentController {
	
	@Autowired
	private StockExtInfoService stockExtInfoService;
	
	@RequestMapping(value = "/stock/ext/{id}", method = RequestMethod.GET, headers = "version=1.0.0")
	public StockExtInfoEntity get(@PathVariable(value = "id") int id) {
		return stockExtInfoService.get(id);
	}
	
	@RequestMapping(value = "/stock/ext", method = RequestMethod.POST, headers = "version=1.0.0")
	public StockExtInfoEntity add(@RequestBody StockExtInfoEntity entity) {
		
		return stockExtInfoService.add(entity);
	}
	
	@RequestMapping(value = "/stock/ext/{id}", method = RequestMethod.PUT, headers = "version=1.0.0")
	public StockExtInfoEntity update(@PathVariable(value = "id") int id, 
										@RequestBody StockExtInfoEntity entity) {
		entity.setId(id);
		return stockExtInfoService.update(entity);
	}
	
	@RequestMapping(value = "/stock/ext/{id}", method = RequestMethod.DELETE, headers = "version=1.0.0")
	public void delete(@PathVariable(value = "id") int id) {
		
		stockExtInfoService.delete(id);
	}
	
}
