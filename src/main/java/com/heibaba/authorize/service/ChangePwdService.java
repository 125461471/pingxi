package com.heibaba.authorize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heibaba.common.exception.ChangePwdException;
import com.heibaba.usercenter.entity.rdb.UserEntity;
import com.heibaba.usercenter.repository.UserRepository;

/**
 * 用户密码修改服务类
 * @author shenhl
 *
 */
@Service
@Transactional
public class ChangePwdService {
	
	@Autowired
	UserRepository repository;
	
	/**
	 * 修改用户密码
	 * @param userId 用户ID
	 * @param oldPassword 加密后的原密码
	 * @param newPassword 加密后的新密码
	 * @throws ChangePwdException
	 */
	public void change(int userId, String oldPassword, String newPassword) {
		
		//1、校验原密码正确性
		boolean oldPwdOk = checkOldPwd(userId, oldPassword);
		//2、更改数据库密码
		if (oldPwdOk) {
			repository.changePwd(userId, newPassword);
		}
	}
	
	/**
	 * 验证原密码正确性
	 * @param userId 用户ID
	 * @param oldPassword 加密后的原密码
	 * @return
	 * @throws ChangePwdException
	 */
	private boolean checkOldPwd(int userId, String oldPassword) {
		
		boolean res = false;
		UserEntity userEntity = repository.findOne(userId);
		if (userEntity != null & userEntity.getPassword().equals(oldPassword)) {
			res = true;
		} else {
			throw new ChangePwdException("原密码错误。");
		}
		
		return res;
	}
	
}
