package com.heibaba.common.utils;

/**
 * 密码工具类
 * @author shenhl
 *
 */
public class PasswordUtil {

	/**
	 * 判断密码是否符合要求(长度为[6~30])
	 * @param accountName
	 * @return
	 */
	public static boolean isPassword(String pwd) {
		
		boolean result = false;
		if (null != pwd && pwd.length() > 5 && pwd.length() < 31) {
			result = true;
		} 
		
		return result;
	}
	
}