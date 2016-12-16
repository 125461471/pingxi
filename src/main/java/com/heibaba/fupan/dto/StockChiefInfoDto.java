package com.heibaba.fupan.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "stockChiefInfo")
public class StockChiefInfoDto {
	
	@Id
	private String daima;
	
	private String pinyin;
	
	private String zhongwenming;
	
	public StockChiefInfoDto(String daima, String pinyin, String zhongwenming) {
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