package com.heibaba.common;

import java.util.HashMap;
import java.util.LinkedList;

import com.heibaba.fupan.dto.AutoCompleteDto;
import com.heibaba.fupan.dto.StockCacheDto;

public class Global {
	
	public static HashMap<String, StockCacheDto> stockMap = new HashMap<String, StockCacheDto>();
	public static LinkedList<AutoCompleteDto> stockList = new LinkedList<AutoCompleteDto>();
}
