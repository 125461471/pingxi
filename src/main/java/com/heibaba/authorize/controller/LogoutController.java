package com.heibaba.authorize.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heibaba.authorize.model.UserContextModel;

@RestController
@RequestMapping("/user")
public class LogoutController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);
	
	@Value("${server.context-path}")
	private String contextPath;
	@Value("${app.login-path}")
	private String loginPath;

	@RequestMapping(value = "/logout", method = RequestMethod.GET, headers = "version=1.0.0")
	public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		UserContextModel userModel = (UserContextModel)session.getAttribute("USER_CONTEXT_MODEL");
		if (userModel != null) {
			session.setAttribute("USER_CONTEXT_MODEL", null);
			logger.debug("注销成功，欢迎再来 "+userModel.getUserName());
		}
		
		response.sendRedirect(contextPath+loginPath);
	}

}
