package com.heibaba.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date dateBefore(int day) {
		
		return date(new Date(), -day);
	}
	
	public static Date dateAfter(int day) {
		
		return date(new Date(), day);
	}
	
	public static Date dateBefore(Date date, int day) {
		
		return date(date, -day);
	}
	
	public static Date dateAfter(Date date, int day) {
		
		return date(date, day);
	}
	
	private static Date date(Date date, int day) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		Date newDate = cal.getTime();
		
		return newDate;
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
	
	public static boolean sameMonth(Date date1, Date date2) {
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		
		return cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
	}
	
	public static boolean sameQuarter(Date date1, Date date2) {
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		
		return  getQuarter(cal1.get(Calendar.MONTH) + 1) == getQuarter(cal2.get(Calendar.MONTH) + 1);
	}
	
	public static boolean sameYear(Date date1, Date date2) {
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
	}
	
	public static int getQuarter(int month) {
		
		int quarter = 0;
		if (month > 0 && month < 4) {
			quarter = 1;
		} else if (month > 3 && month < 7) {
			quarter = 2;
		} else if (month > 6 && month < 10) {
			quarter = 3;
		} else if (month > 9 && month < 13) {
			quarter = 4;
		}
		
		return quarter;
	}
}
