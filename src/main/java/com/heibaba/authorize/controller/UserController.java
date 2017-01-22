package com.heibaba.authorize.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.authorize.model.UserContextModel;
import com.heibaba.authorize.service.ChangePwdService;
import com.heibaba.common.exception.ChangePwdException;
import com.heibaba.common.utils.Md5Util;
import com.heibaba.common.utils.PasswordUtil;

@RestController
@RequestMapping("/services/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ChangePwdService changePwdService;

	@RequestMapping(value = "/pwd/change", method = RequestMethod.PUT, headers = "version=1.0.0")
	public void change(@RequestParam(value = "oldPassword", required = true) String oldPassword,
					   @RequestParam(value = "newPassword", required = true) String newPassword,
					   HttpSession session) {
		
		if (!PasswordUtil.isPassword(newPassword)) {
			throw new ChangePwdException("新密码格式错误,密码长度要求[6~30]位.");
		}
		UserContextModel userModel = UserContextModel.getCurrentUser();
		if (userModel != null) {
			changePwdService.change(userModel.getUserId(), Md5Util.MD5(oldPassword), Md5Util.MD5(newPassword));
			logger.debug("密码修改成功");
		}
	}
	
	/**
	 * 当前登录人信息
	 * @return
	 */
	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	public UserContextModel getUserInfo() {
		
		return UserContextModel.getCurrentUser();
	}
	
	/**
	 * 密码找回
	 */
	@RequestMapping(value = "/pwd/retrieve", method = RequestMethod.POST, headers = "version=1.0.0")
	public void pwdRetrieve() {
		
		//== to do ==> 密码找回
		//发送找回短信时，设置每分钟条数限制及每天条数限制。
		//短信验证码的时效性。
	}
	
}
