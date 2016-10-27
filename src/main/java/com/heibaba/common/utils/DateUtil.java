package com.heibaba.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date dateBefore(int day) {
		
		return date(-day);
	}
	
	public static Date dateAfter(int day) {
		
		return date(day);
	}
	
	private static Date date(int day) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, day);
		Date date = cal.getTime();
		
		return date;
	}
	
	public static Date strToDate(String dateStr, String formatStr) {
		
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String dateToStr(Date date, String formatStr) {
		
		String result = "";
		try {
			if (date != null) {
				DateFormat sdf =  new SimpleDateFormat(formatStr);
				result = sdf.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
