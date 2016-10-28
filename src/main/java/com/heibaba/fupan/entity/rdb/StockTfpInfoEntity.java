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
import com.heibaba.auditor.BaseEntity;

@Entity
@Table(name="stock_tfp_info")
@JsonIgnoreProperties(value={"jiluDate","hibernateLazyInitializer","handler","fieldHandler"})
public class StockTfpInfoEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Size(min=6, max=6)
	@Column(name = "daima")
	private String daima;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "tingpai_date")
	private Date tingpaiDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fupai_date")
	private Date fupaiDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTingpaiDate() {
		return tingpaiDate;
	}

	public void setTingpaiDate(Date tingpaiDate) {
		this.tingpaiDate = tingpaiDate;
	}

	public Date getFupaiDate() {
		return fupaiDate;
	}

	public void setFupaiDate(Date fupaiDate) {
		this.fupaiDate = fupaiDate;
	}

	public String getDaima() {
		return daima;
	}

	public void setDaima(String daima) {
		this.daima = daima;
	}

}