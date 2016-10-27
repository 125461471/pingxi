package com.heibaba.usercenter.entity.rdb;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heibaba.autidor.BaseEntity;

@Entity
@Table(name="assets")
@JsonIgnoreProperties(value={"jiluDate","hibernateLazyInitializer","handler","fieldHandler"})
public class AssetsEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "user_id")
	private int userId;
	
	@NotNull
	@Column(name = "account_id")
	private int accountId;
	
//	/**
//	 * 昨日资产
//	 */
//	@Column(name = "pre_assets")
//	private Integer preAssets;
	
	/**
	 * 今日资产
	 */
	@NotNull
	@Min(0)
	@Column(name = "assets")
	private double assets;
	
	/**
	 * 今日资金转账[入账+/出账-]
	 */
	@Column(name = "transfer")
	private double transfer;
	
	/**
	 * 今日仓位
	 */
	@Min(0)
	@Max(200)
	@Column(name = "freight_space")
	private int freightSpace;
	
	/**
	 * 今日停牌仓位
	 */
	@Min(0)
	@Max(200)
	@Column(name = "tp_freight_space")
	private int tpFreightSpace;
	
	/**
	 * 资产日期
	 * @return
	 */
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "assets_date")
	private Date assetsDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAssets() {
		return assets;
	}

	public void setAssets(double assets) {
		this.assets = assets;
	}

	public double getTransfer() {
		return transfer;
	}

	public void setTransfer(double transfer) {
		this.transfer = transfer;
	}

	public int getFreightSpace() {
		return freightSpace;
	}

	public void setFreightSpace(int freightSpace) {
		this.freightSpace = freightSpace;
	}

	public int getTpFreightSpace() {
		return tpFreightSpace;
	}

	public void setTpFreightSpace(int tpFreightSpace) {
		this.tpFreightSpace = tpFreightSpace;
	}

	public Date getAssetsDate() {
		return assetsDate;
	}

	public void setAssetsDate(Date assetsDate) {
		this.assetsDate = assetsDate;
	}

}