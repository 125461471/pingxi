package com.heibaba.usercenter.controller.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.common.exception.BusinessException;
import com.heibaba.common.utils.Md5Util;
import com.heibaba.common.utils.PasswordUtil;
import com.heibaba.common.utils.UserNameUtil;
import com.heibaba.usercenter.entity.rdb.UserEntity;
import com.heibaba.usercenter.service.UserService;

@RestController
public class UserController extends UcConsoleParentController {
	
	/**
	 * 配置默认密码
	 */
	@Value("${app.conf.default-pwd}")
	private String defaultPwd;
	
	@Autowired
	private UserService service;

	@RequestMapping(value = "/user", method = RequestMethod.POST, headers = "version=1.0.0")
	public void userAdd(@RequestBody UserEntity entity) {

		//判断用户名及手机号合法性
		if (!UserNameUtil.isUserName(entity.getUserName())) {
			throw new BusinessException("用户名格式错误.");
		}
		if (!UserNameUtil.isTelephone(entity.getTelephone())) {
			throw new BusinessException("手机号格式错误.");
		}
		//验证密码合法性
		if (!PasswordUtil.isPassword(entity.getPassword())) {
			throw new BusinessException("密码格式错误.");
		}
		//判断用户名及手机号是否存在
		if (service.checkUserNameExist(entity.getUserName())) {
			throw new BusinessException("此用户名已注册.");
		}
		if (service.checkTelephoneExist(entity.getTelephone())) {
			throw new BusinessException("此手机号已注册.");
		}
		
		entity.setPassword(Md5Util.MD5(defaultPwd));
		entity.setLocked(0);
		entity.setEnabled(1);
		service.addUser(entity);
	}

	@RequestMapping(value = "/user/{id}/pwdreset", method = RequestMethod.PUT, headers = "version=1.0.0")
	public void pwdReset(@PathVariable(value = "id") int id) {
		
		service.changePwd(id, Md5Util.MD5(defaultPwd));
	}
	
	@RequestMapping(value = "/user/{id}/username", method = RequestMethod.PUT, headers = "version=1.0.0")
	public void changeUserName(@PathVariable(value = "id") int id,
								@RequestParam("username") String username) {
		
		service.changeUserName(id, username);
	}
	
	@RequestMapping(value = "/user/{id}/telephone", method = RequestMethod.PUT, headers = "version=1.0.0")
	public void pwdReset(@PathVariable(value = "id") int id,
							@RequestParam("telephone") String telephone) {
		
		service.changeTelephone(id, telephone);
	}
	
	@RequestMapping(value = "/user/{id}/lock", method = RequestMethod.PUT, headers = "version=1.0.0")
	public void userLock(@PathVariable(value = "id") int id) {

		service.lockUser(id);
	}

	@RequestMapping(value = "/user/{id}/unlock", method = RequestMethod.PUT, headers = "version=1.0.0")
	public void userUnlock(@PathVariable(value = "id") int id) {

		service.unlockUser(id);
	}

	@RequestMapping(value = "/user/{id}/enable", method = RequestMethod.PUT, headers = "version=1.0.0")
	public void userEnable(@PathVariable(value = "id") int id) {

		service.enableUser(id);
	}

	@RequestMapping(value = "/user/{id}/forbidden", method = RequestMethod.PUT, headers = "version=1.0.0")
	public void userForbidden(@PathVariable(value = "id") int id) {

		service.forbiddenUser(id);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, headers = "version=1.0.0")
	public void userUpdate(@PathVariable(value = "id") int id,
							@RequestBody UserEntity entity) {
		
		entity.setUserId(id);
		UserEntity record = service.getUser(id);
		entity.setPassword(record.getPassword());
		entity.setUserName(record.getUserName());
		entity.setTelephone(record.getTelephone());
		service.updateUser(entity);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "version=1.0.0")
	public UserEntity userGet(@PathVariable(value = "id") int id) {

		return service.getUser(id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, headers = "version=1.0.0")
	public Page<UserEntity> userList(@RequestParam(value = "userName", required = false, defaultValue = "") String userName,
										@RequestParam(value = "nickName", required = false, defaultValue = "") String nickName, 
										Pageable pageable) {

		Page<UserEntity> list = service.listUser(userName, nickName, pageable);

		return list;
	}

}
