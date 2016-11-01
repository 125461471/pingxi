package com.heibaba.common.exception;

/**
 * 用户身份认证异常类
 * @author shenhl
 *
 */
public class UserAuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;  
	  
    public UserAuthException(String message) {  
        super(message);  
    }  
}
