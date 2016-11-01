package com.heibaba.authorize.dto;

import java.io.Serializable;

import com.heibaba.authorize.component.AuthAccount;

/**
 * 用户域DTO
 * @author shenhl
 *
 */
public class UserScopeDto implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
     * 用户(neuq1fs2admin、 web)
     */
	private String scope;
    /**
     * 用户名（管理员用户、互联网用户）
     */
	private String name;
	/**
	 * 分组(neuq1fs2admin@、web@)
	 */
	private String group;

	private AuthAccount authAccountImpl;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public AuthAccount getAuthAccountImpl() {
		return authAccountImpl;
	}

	public void setAuthAccountImpl(AuthAccount authAccountImpl) {
		this.authAccountImpl = authAccountImpl;
	}

}
