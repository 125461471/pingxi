package com.heibaba.authorize.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heibaba.authorize.dto.RegisterDto;
import com.heibaba.authorize.model.UserContextModel;
import com.heibaba.common.exception.BusinessException;
import com.heibaba.common.utils.Md5Util;
import com.heibaba.common.utils.PasswordUtil;
import com.heibaba.common.utils.UserNameUtil;
import com.heibaba.usercenter.entity.rdb.UserEntity;
import com.heibaba.usercenter.service.UserService;

/**
 * 用户注册服务类
 * @author shenhl
 *
 */
@Service
@Transactional
public class RegisterService {
	
	@Autowired
	UserService userService;

	/**
	 * 用户注册
	 * 
	 */
	public UserContextModel register(RegisterDto registerDto) {
		
		//判断用户名及手机号合法性
		if (!UserNameUtil.isUserName(registerDto.getUserName())) {
			throw new BusinessException("用户名格式错误.");
		}
		if (!UserNameUtil.isTelephone(registerDto.getTelephone())) {
			throw new BusinessException("手机号格式错误.");
		}
		//验证密码合法性
		if (!PasswordUtil.isPassword(registerDto.getPassword())) {
			throw new BusinessException("密码格式错误.");
		}
		//判断用户名及手机号是否存在
		if (userService.checkUserNameExist(registerDto.getUserName())) {
			throw new BusinessException("此用户名已注册.");
		}
		if (userService.checkTelephoneExist(registerDto.getTelephone())) {
			throw new BusinessException("此手机号已注册.");
		}
		
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(registerDto, entity);
		entity.setLocked(0);
		entity.setEnabled(1);
		entity.setPassword(Md5Util.MD5(registerDto.getPassword()));
		userService.addUser(entity);
		
		return toUserContextModel(entity);
	}
	
	private UserContextModel toUserContextModel(UserEntity entity) {
		
		UserContextModel model = new UserContextModel();
		BeanUtils.copyProperties(entity, model);
		
		return model;
	}
}
