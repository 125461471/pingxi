package com.heibaba.fupan.dto;

import java.io.Serializable;

public class StockBaseInfoDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String daima;
	
	private String zhongwenming;
	
//	private String pinyin;
	
	private int rongzi;
	
	private String quyu;
	
	private String bankuai;

	public String getDaima() {
		return daima;
	}

	public void setDaima(String daima) {
		this.daima = daima;
	}

	public String getZhongwenming() {
		return zhongwenming;
	}

	public void setZhongwenming(String zhongwenming) {
		this.zhongwenming = zhongwenming;
	}

	public int getRongzi() {
		return rongzi;
	}

	public void setRongzi(int rongzi) {
		this.rongzi = rongzi;
	}

	public String getQuyu() {
		return quyu;
	}

	public void setQuyu(String quyu) {
		this.quyu = quyu;
	}

	public String getBankuai() {
		return bankuai;
	}

	public void setBankuai(String bankuai) {
		this.bankuai = bankuai;
	}
	
}