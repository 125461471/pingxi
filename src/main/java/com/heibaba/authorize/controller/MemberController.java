package com.heibaba.authorize.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.authorize.dto.Member;
import com.heibaba.authorize.dto.RegisterDto;
import com.heibaba.authorize.model.UserContextModel;
import com.heibaba.authorize.service.AuthService;
import com.heibaba.authorize.service.RegisterService;
import com.heibaba.usercenter.service.UserService;

@RestController 
@RequestMapping("/open/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Value("${server.context-path}")
	private String contextPath;
	@Value("${app.login-path}")
	private String loginPath;
	
	@Autowired
	private AuthService authService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private UserService service;

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
			logger.debug("登录成功，欢迎"+userModel.getUserName()+"["+userModel.getNickName()+"]");
		}
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET, headers = "version=1.0.0")
	public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		UserContextModel userModel = (UserContextModel)session.getAttribute("USER_CONTEXT_MODEL");
		if (userModel != null) {
			session.setAttribute("USER_CONTEXT_MODEL", null);
			logger.debug("注销成功，欢迎再来 "+userModel.getUserName()+"["+userModel.getNickName()+"]");
		}
		
		response.sendRedirect(contextPath+loginPath);
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
		if (userModel != null && !StringUtils.isEmpty(userModel.getUserId())) {//注册成功
			session.setAttribute("USER_CONTEXT_MODEL", userModel);
			logger.debug("登录成功，欢迎"+registerDto.getUserName());
		}
	}
	
	/**
	 * 用户名唯一性校验
	 * @param username
	 * @return true：存在；false：不存在
	 */
	@RequestMapping(value = "/username/exist", method = RequestMethod.GET, headers = "version=1.0.0")
	public boolean usernameExist(@RequestParam("userName") String userName) {
		
		return service.checkUserNameExist(userName);
	}
	
}
