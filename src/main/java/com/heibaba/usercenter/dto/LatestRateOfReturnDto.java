package com.heibaba.usercenter.dto;

public class LatestRateOfReturnDto {
	
	/**
	 * 本月收益率
	 */
	private double rateOfMonthReturn;
	/**
	 * 本年收益率
	 */
	private double rateOfYearReturn;
	
	public double getRateOfMonthReturn() {
		return rateOfMonthReturn;
	}
	public void setRateOfMonthReturn(double rateOfMonthReturn) {
		this.rateOfMonthReturn = rateOfMonthReturn;
	}
	public double getRateOfYearReturn() {
		return rateOfYearReturn;
	}
	public void setRateOfYearReturn(double rateOfYearReturn) {
		this.rateOfYearReturn = rateOfYearReturn;
	}
	
}