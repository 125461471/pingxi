package com.heibaba.usercenter.entity.rdb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heibaba.auditor.BaseEntity;

/**
 * 资产收益率
 * @author shenhl
 *
 */
@Entity
@Table(name="assets_rate_of_return")
@JsonIgnoreProperties(value={"jiluDate","hibernateLazyInitializer","handler","fieldHandler"})
public class AssetsRateOfReturnEntity extends BaseEntity implements Serializable {
	
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
	
	@NotNull
	@Column(name = "year")
	private int year;

	@Column(name = "rate_of_year")
	private double rateOfYear;

	@Column(name = "profit_of_year")
	private double profitOfYear;
	
	@Column(name = "rate_of_month_01")
	private double rateOfMonth01;
	@Column(name = "rate_of_month_02")
	private double rateOfMonth02;
	@Column(name = "rate_of_month_03")
	private double rateOfMonth03;
	@Column(name = "rate_of_month_04")
	private double rateOfMonth04;
	@Column(name = "rate_of_month_05")
	private double rateOfMonth05;
	@Column(name = "rate_of_month_06")
	private double rateOfMonth06;
	@Column(name = "rate_of_month_07")
	private double rateOfMonth07;
	@Column(name = "rate_of_month_08")
	private double rateOfMonth08;
	@Column(name = "rate_of_month_09")
	private double rateOfMonth09;
	@Column(name = "rate_of_month_10")
	private double rateOfMonth10;
	@Column(name = "rate_of_month_11")
	private double rateOfMonth11;
	@Column(name = "rate_of_month_12")
	private double rateOfMonth12;

	@Column(name = "profit_of_month_01")
	private double profitOfMonth01;
	@Column(name = "profit_of_month_02")
	private double profitOfMonth02;
	@Column(name = "profit_of_month_03")
	private double profitOfMonth03;
	@Column(name = "profit_of_month_04")
	private double profitOfMonth04;
	@Column(name = "profit_of_month_05")
	private double profitOfMonth05;
	@Column(name = "profit_of_month_06")
	private double profitOfMonth06;
	@Column(name = "profit_of_month_07")
	private double profitOfMonth07;
	@Column(name = "profit_of_month_08")
	private double profitOfMonth08;
	@Column(name = "profit_of_month_09")
	private double profitOfMonth09;
	@Column(name = "profit_of_month_10")
	private double profitOfMonth10;
	@Column(name = "profit_of_month_11")
	private double profitOfMonth11;
	@Column(name = "profit_of_month_12")
	private double profitOfMonth12;

	public double getRateOfMonth01() {
		return rateOfMonth01;
	}

	public void setRateOfMonth01(double rateOfMonth01) {
		this.rateOfMonth01 = rateOfMonth01;
	}

	public double getRateOfMonth02() {
		return rateOfMonth02;
	}

	public void setRateOfMonth02(double rateOfMonth02) {
		this.rateOfMonth02 = rateOfMonth02;
	}

	public double getRateOfMonth03() {
		return rateOfMonth03;
	}

	public void setRateOfMonth03(double rateOfMonth03) {
		this.rateOfMonth03 = rateOfMonth03;
	}

	public double getRateOfMonth04() {
		return rateOfMonth04;
	}

	public void setRateOfMonth04(double rateOfMonth04) {
		this.rateOfMonth04 = rateOfMonth04;
	}

	public double getRateOfMonth05() {
		return rateOfMonth05;
	}

	public void setRateOfMonth05(double rateOfMonth05) {
		this.rateOfMonth05 = rateOfMonth05;
	}

	public double getRateOfMonth06() {
		return rateOfMonth06;
	}

	public void setRateOfMonth06(double rateOfMonth06) {
		this.rateOfMonth06 = rateOfMonth06;
	}

	public double getRateOfMonth07() {
		return rateOfMonth07;
	}

	public void setRateOfMonth07(double rateOfMonth07) {
		this.rateOfMonth07 = rateOfMonth07;
	}

	public double getRateOfMonth08() {
		return rateOfMonth08;
	}

	public void setRateOfMonth08(double rateOfMonth08) {
		this.rateOfMonth08 = rateOfMonth08;
	}

	public double getRateOfMonth09() {
		return rateOfMonth09;
	}

	public void setRateOfMonth09(double rateOfMonth09) {
		this.rateOfMonth09 = rateOfMonth09;
	}

	public double getRateOfMonth10() {
		return rateOfMonth10;
	}

	public void setRateOfMonth10(double rateOfMonth10) {
		this.rateOfMonth10 = rateOfMonth10;
	}

	public double getRateOfMonth11() {
		return rateOfMonth11;
	}

	public void setRateOfMonth11(double rateOfMonth11) {
		this.rateOfMonth11 = rateOfMonth11;
	}

	public double getRateOfMonth12() {
		return rateOfMonth12;
	}

	public void setRateOfMonth12(double rateOfMonth12) {
		this.rateOfMonth12 = rateOfMonth12;
	}

	public double getProfitOfMonth01() {
		return profitOfMonth01;
	}

	public void setProfitOfMonth01(double profitOfMonth01) {
		this.profitOfMonth01 = profitOfMonth01;
	}

	public double getProfitOfMonth02() {
		return profitOfMonth02;
	}

	public void setProfitOfMonth02(double profitOfMonth02) {
		this.profitOfMonth02 = profitOfMonth02;
	}

	public double getProfitOfMonth03() {
		return profitOfMonth03;
	}

	public void setProfitOfMonth03(double profitOfMonth03) {
		this.profitOfMonth03 = profitOfMonth03;
	}

	public double getProfitOfMonth04() {
		return profitOfMonth04;
	}

	public void setProfitOfMonth04(double profitOfMonth04) {
		this.profitOfMonth04 = profitOfMonth04;
	}

	public double getProfitOfMonth05() {
		return profitOfMonth05;
	}

	public void setProfitOfMonth05(double profitOfMonth05) {
		this.profitOfMonth05 = profitOfMonth05;
	}

	public double getProfitOfMonth06() {
		return profitOfMonth06;
	}

	public void setProfitOfMonth06(double profitOfMonth06) {
		this.profitOfMonth06 = profitOfMonth06;
	}

	public double getProfitOfMonth07() {
		return profitOfMonth07;
	}

	public void setProfitOfMonth07(double profitOfMonth07) {
		this.profitOfMonth07 = profitOfMonth07;
	}

	public double getProfitOfMonth08() {
		return profitOfMonth08;
	}

	public void setProfitOfMonth08(double profitOfMonth08) {
		this.profitOfMonth08 = profitOfMonth08;
	}

	public double getProfitOfMonth09() {
		return profitOfMonth09;
	}

	public void setProfitOfMonth09(double profitOfMonth09) {
		this.profitOfMonth09 = profitOfMonth09;
	}

	public double getProfitOfMonth10() {
		return profitOfMonth10;
	}

	public void setProfitOfMonth10(double profitOfMonth10) {
		this.profitOfMonth10 = profitOfMonth10;
	}

	public double getProfitOfMonth11() {
		return profitOfMonth11;
	}

	public void setProfitOfMonth11(double profitOfMonth11) {
		this.profitOfMonth11 = profitOfMonth11;
	}

	public double getProfitOfMonth12() {
		return profitOfMonth12;
	}

	public void setProfitOfMonth12(double profitOfMonth12) {
		this.profitOfMonth12 = profitOfMonth12;
	}

	public double getRateOfYear() {
		return rateOfYear;
	}

	public void setRateOfYear(double rateOfYear) {
		this.rateOfYear = rateOfYear;
	}

	public double getProfitOfYear() {
		return profitOfYear;
	}

	public void setProfitOfYear(double profitOfYear) {
		this.profitOfYear = profitOfYear;
	}

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}