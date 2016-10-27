package com.heibaba.fupan.dto;

import java.io.Serializable;

import com.heibaba.fupan.entity.rdb.StockTxInfoEntity;

public class StockTxInfoDto extends StockTxInfoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String zhongwenming;

	public String getZhongwenming() {
		return zhongwenming;
	}

	public void setZhongwenming(String zhongwenming) {
		this.zhongwenming = zhongwenming;
	}
	
}