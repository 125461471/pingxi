package com.heibaba.common.exception;

/**
 * 密码修改异常类
 * @author shenhl
 *
 */
public class ChangePwdException extends RuntimeException {

	private static final long serialVersionUID = 1L;  
	  
    public ChangePwdException(String message) {  
        super(message);  
    }  
}
