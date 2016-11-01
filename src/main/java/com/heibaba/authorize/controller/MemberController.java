package com.heibaba.authorize.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.authorize.dto.Member;
import com.heibaba.authorize.dto.RegisterDto;
import com.heibaba.authorize.model.UserContextModel;
import com.heibaba.authorize.service.AuthService;
import com.heibaba.authorize.service.RegisterService;

@RestController 
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private AuthService authService;
	@Autowired
	private RegisterService registerService;
	
	/**
	 * 用户登录
	 * @param member
	 * @param session
	 * @param response
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "version=1.0.0")
	public void login(@RequestBody Member member,
					  HttpSession session,
					  HttpServletResponse response) {
		
		UserContextModel userModel = authService.auth(member.getAccountName(), member.getPassword(), member.getScope());
		if (userModel != null) {//登录成功
			session.setAttribute("USER_CONTEXT_MODEL", userModel);
			logger.debug("登录成功，欢迎"+member.getAccountName());
		}
	}
	
	/**
	 * 用户注册
	 * @param registerDto
	 * @param session
	 * @param response
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "version=1.0.0")
	public void register(@RequestBody RegisterDto registerDto,
						  HttpSession session,
						  HttpServletResponse response) {
		
		//== to do ==> 用户注册
		//第一步，用户输入手机号；
		//第二步，用户点击获取短信验证码，后台验证手机号合法性，合法则放入session中，并发送短信验证码（发送短信时，设置每分钟条数限制及每天条数限制。）；
		//第三步，用户输入短信验证码，后台验证短信验证码合法性，合法则校验手机号是否存在（验证时，注意短信验证码的时效性。）；
		//第四步，用户输入用户名及密码，后台验证用户名及密码的合法性，合法则校验用户名是否存在。
		UserContextModel userModel = null;
		userModel = registerService.register(registerDto);
		if (userModel != null && userModel.getUserId() > 0) {//注册成功
			session.setAttribute("USER_CONTEXT_MODEL", userModel);
			logger.debug("登录成功，欢迎"+registerDto.getUserName());
		}
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
