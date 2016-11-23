package com.heibaba.usercenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.usercenter.entity.rdb.AssetsEntity;
import com.heibaba.usercenter.service.AssetsService;

@RestController
public class AssetsController extends UcParentController {
	
	@Autowired
	private AssetsService assetsService;
	
	@RequestMapping(value = "/account/{accountId}/assets/{id}", method = RequestMethod.GET, headers = "version=1.0.0")
	public AssetsEntity get(@PathVariable(value = "accountId") int accountId, 
							@PathVariable(value = "id") int id) {
		
		int userId = 1;//== to do ==>获取session中的userId ;
		return assetsService.get(id, userId, accountId);
	}
	
	@RequestMapping(value = "/account/{accountId}/assets", method = RequestMethod.POST, headers = "version=1.0.0")
	public AssetsEntity add(@PathVariable(value = "accountId") int accountId, 
							@RequestBody AssetsEntity entity) {
		
		entity.setUserId(1);//== to do ==>获取session中的userId 
		entity.setAccountId(accountId);
		return assetsService.add(entity);//== to do ==> 判断userId/accountId/assetsDate唯一性
	}
	
	@RequestMapping(value = "/account/{accountId}/assets/{id}", method = RequestMethod.PUT, headers = "version=1.0.0")
	public AssetsEntity update(@PathVariable(value = "accountId") int accountId, 
								@PathVariable(value = "id") int id, 
								@RequestBody AssetsEntity entity) {
		
		entity.setUserId(1);//== to do ==>获取session中的userId 
		entity.setAccountId(accountId);
		entity.setId(id);
		return assetsService.update(entity);
	}
	
	@RequestMapping(value = "/account/{accountId}/assets/{id}", method = RequestMethod.DELETE, headers = "version=1.0.0")
	public void delete(@PathVariable(value = "accountId") int accountId, 
						@PathVariable(value = "id") int id) {
		
		int userId = 1;//== to do ==>获取session中的userId ;
		assetsService.delete(id, userId, accountId);
	}
	
	@RequestMapping(value = "/account/{accountId}/assets", method = RequestMethod.GET, headers = "version=1.0.0")
	public Page<AssetsEntity> list(@PathVariable(value = "accountId") int accountId, 
									Pageable pageable) {
		
		int userId = 1;//== to do ==>获取session中的userId 
		return assetsService.list(userId, accountId, pageable);
	}
	
	@RequestMapping(value = "/account/{accountId}/assets/premonth/eom", method = RequestMethod.GET, headers = "version=1.0.0")
	public AssetsEntity getPreMonthEomAssets(@PathVariable(value = "accountId") int accountId) {
		
		int userId = 1;//== to do ==>获取session中的userId ;
		return assetsService.getPreMonthAssets(userId, accountId);
	}
	
}
