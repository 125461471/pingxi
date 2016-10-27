package com.heibaba.autidor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity {
	
	/**
	 * 记录时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "jilu_date")
	private Date jiluDate;
	
    @PrePersist
    public void prePersist() {
        this.jiluDate = new Date();
    }
      
    @PreUpdate
    public void preUpdate() {
        this.jiluDate = new Date();
    }

	public Date getJiluDate() {
		return jiluDate;
	}

	public void setJiluDate(Date jiluDate) {
		this.jiluDate = jiluDate;
	}
    
}