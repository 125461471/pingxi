package com.heibaba.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户名工具类
 * @author shenhl
 *
 */
public class UserNameUtil {

	/**
	 * 判断用户名的类型
	 * @param accountName
	 * @return
	 */
	public static UserNameType type(String accountName) {
		
		if (isTelephone(accountName)) {
			return UserNameType.TELEPHONE;
		} else if (isUserName(accountName)) {
			return UserNameType.USERNAME;
		}
		
		return UserNameType.NONE;
	}
	
	/**
	 * 判断用户名是否为普通的账户名形式，长度为[3~20]
	 * @param accountName
	 * @return
	 */
	public static boolean isUserName(String accountName) {
		
		boolean result = false;
		if (null != accountName && accountName.length() > 2 && accountName.length() < 21) {
			result = true;
		} 
		
		return result;
	}
	
	/**
	 * 判断用户名是否为手机号
	 * @param accountName
	 * @return
	 */
	public static boolean isTelephone(String accountName) {
		
		boolean result = false;
		String pattern = "^((13[0-9])|(15[^4,\\D])|(17(6|0))|(18[0-9]))\\d{8}$";
		if (null != accountName && accountName.length() == 11) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(accountName);
			result = m.matches();
		}
		
		return result;
	}
	
}