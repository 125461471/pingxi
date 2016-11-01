package com.heibaba.authorize.dto;

import java.io.Serializable;

/**
 * 用户注册DTO
 * @author shenhl
 *
 */
public class RegisterDto extends UserAccount implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
     * 密码
     */
	private String password;
	/**
	 * 短信验证码
	 */
	private String code;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
