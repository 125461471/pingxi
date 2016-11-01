package com.heibaba.auditor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.heibaba.authorize.model.UserContextModel;

@MappedSuperclass
public abstract class BaseEntity2 {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "create_user_id")
	private String createUserId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_user_id")
	private String updateUserId;
	
    @PrePersist
    public void prePersist() {
    	UserContextModel user = UserContextModel.getCurrentUser();
        this.createTime = new Date();
        this.createUserId = String.valueOf(user.getUserId());
    }
      
    @PreUpdate
    public void preUpdate() {
    	UserContextModel user = UserContextModel.getCurrentUser();
        this.updateTime = new Date();
        this.updateUserId = String.valueOf(user.getUserId());
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

}