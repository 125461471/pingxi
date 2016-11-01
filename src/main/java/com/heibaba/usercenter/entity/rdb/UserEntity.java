package com.heibaba.usercenter.entity.rdb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.heibaba.auditor.BaseEntity2;

@Entity
@Table(name = "au_user")
public class UserEntity extends BaseEntity2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键，自动增长,id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;
	/**
	 * 手机号
	 */
	@Column(name = "telephone")
	private String telephone;
	/**
	 * 是否可用
	 */
	@Column(name = "enabled")
	private int enabled;
	/**
	 * 是否锁定
	 */
	@Column(name = "locked")
	private int locked;
	/**
	 * 用户类别
	 */
	@Column(name = "type")
	private String type;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}