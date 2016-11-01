package com.heibaba.authorize.component;

import com.heibaba.authorize.model.UserContextModel;
import com.heibaba.common.exception.UserAuthException;

/**
 * 用户认证接口类
 * @author shenhl
 *
 */
public interface AuthAccount {
	
	/**
     * 用户信息
     * @param accountName  账号 
     * @param password  密码
     * @return
     * @throws UserAuthException
     */
	UserContextModel userName(String accountName, String password) throws UserAuthException;
	
	/**
     * 手机号
     * @param accountName  账号 
     * @param password  密码
     * @return
     * @throws UserAuthException
     */
	UserContextModel telephone(String accountName, String password) throws UserAuthException;
}
