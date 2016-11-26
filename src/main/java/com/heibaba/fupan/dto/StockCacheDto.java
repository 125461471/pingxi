package com.heibaba.fupan.dto;

import java.io.Serializable;

public class StockCacheDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String daima;
	
	private String pinyin;
	
	private String zhongwenming;
	
	public StockCacheDto(String daima, String pinyin, String zhongwenming) {
		this.daima = daima;
		this.pinyin = pinyin;
		this.zhongwenming = zhongwenming;
	}
	
	public String getDaima() {
		return daima;
	}

	public void setDaima(String daima) {
		this.daima = daima;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getZhongwenming() {
		return zhongwenming;
	}

	public void setZhongwenming(String zhongwenming) {
		this.zhongwenming = zhongwenming;
	}
}