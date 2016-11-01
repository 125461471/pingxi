package com.heibaba.authorize.dto;

import java.io.Serializable;

/**
 * 用户账户DTO
 * @author shenhl
 *
 */
public class UserAccount implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
	private String userName;
    /**
     * 手机号
     */
	private String telephone;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
