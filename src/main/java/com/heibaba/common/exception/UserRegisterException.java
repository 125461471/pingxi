package com.heibaba.common.exception;

/**
 * 用户注册异常类
 * @author shenhl
 *
 */
public class UserRegisterException extends RuntimeException {

	private static final long serialVersionUID = 1L;  
	  
    public UserRegisterException(String message) {  
        super(message);  
    }  
}
