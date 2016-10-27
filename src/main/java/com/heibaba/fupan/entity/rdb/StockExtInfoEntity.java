package com.heibaba.fupan.entity.rdb;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heibaba.autidor.BaseEntity;

@Entity
@Table(name="stock_ext_info")
@JsonIgnoreProperties(value={"jiluDate","hibernateLazyInitializer","handler","fieldHandler"})
public class StockExtInfoEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@NotNull
	@Size(min=6, max=6)
	@Column(name = "daima")
	private String daima;
	
	@Column(name = "miaoshu")
	private String miaoshu;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fasheng_date")
	private Date fashengDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	public Date getFashengDate() {
		return fashengDate;
	}

	public void setFashengDate(Date fashengDate) {
		this.fashengDate = fashengDate;
	}

	public String getDaima() {
		return daima;
	}

	public void setDaima(String daima) {
		this.daima = daima;
	}
	
}