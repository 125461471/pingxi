package com.heibaba.authorize.dto;

import java.io.Serializable;


/**
 * 用户登录DTO
 * @author shenhl
 *
 */
public class Member implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 用户名
	 */
	private String accountName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 域名
	 */
	private String scope;
	/**
	 * 页面验证码
	 */
	private String code;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}