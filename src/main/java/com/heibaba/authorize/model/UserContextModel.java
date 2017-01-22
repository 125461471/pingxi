package com.heibaba.authorize.model;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 用户基本信息
 * @author shenhl
 *
 */
public class UserContextModel implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户主键ID
	 */
	private int userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 手机号
	 */
	private String telephone;
	/**
	 * 用户类型
	 */
	private String type;
	/**
	 * 角色
	 */
	private List<String> roles;
	
	public static UserContextModel getCurrentUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	return (UserContextModel)request.getSession().getAttribute("USER_CONTEXT_MODEL");
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
