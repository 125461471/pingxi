package com.heibaba.usercenter.dto;

import java.util.Date;

public class AssetsLineDto {
	
	private int accountId;
	
	/**
	 * 今日资产
	 */
	private double assets;
	
	/**
	 * 今日资金转账[入账+/出账-]
	 */
	private double transfer;
	
	/**
	 * 今日仓位
	 */
	private int freightSpace;
	
	/**
	 * 今日停牌资产
	 */
	private int tpAssets;
	
	/**
	 * 资产日期
	 */
	private Date assetsDate;
	
	/**
	 * 今日收益率
	 */
	private double dayRate;
	
	/**
	 * 累计收益率
	 */
	private double accumulationRate;
	
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

	public int getTpAssets() {
		return tpAssets;
	}

	public void setTpAssets(int tpAssets) {
		this.tpAssets = tpAssets;
	}

	public Date getAssetsDate() {
		return assetsDate;
	}

	public void setAssetsDate(Date assetsDate) {
		this.assetsDate = assetsDate;
	}

	public double getDayRate() {
		return dayRate;
	}

	public void setDayRate(double dayRate) {
		this.dayRate = dayRate;
	}

	public double getAccumulationRate() {
		return accumulationRate;
	}

	public void setAccumulationRate(double accumulationRate) {
		this.accumulationRate = accumulationRate;
	}

}