package com.heibaba.authorize.component;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.heibaba.authorize.model.UserContextModel;
import com.heibaba.common.exception.UserAuthException;
import com.heibaba.common.utils.Md5Util;
import com.heibaba.usercenter.entity.rdb.UserEntity;
import com.heibaba.usercenter.repository.UserRepository;

/**
 * admin用户身份认证
 * @author shenhl
 *
 */
@Component(AuthAdminAccountImpl.BEAN_NAME)
public class AuthAdminAccountImpl implements AuthAccount {
	
	public static final String BEAN_NAME = "com.heibaba.authorize.component.authConsoleAccountImpl";
	
	@Autowired
	UserRepository repository;

	@Override
	public UserContextModel userName(String accountName, String password) {
		
		UserEntity userEntity = repository.findByUserName(accountName);//根据用户名获取用户信息实体
		return authAccount(userEntity, password);//验证用户信息是否正常
	}
	
	@Override
	public UserContextModel telephone(String accountName, String password) {
		
		UserEntity userEntity = repository.findByTelephone(accountName);//根据手机号获取用户信息实体
		return authAccount(userEntity, password);//验证用户信息是否正常
	}

	/**
	 * 验证用户信息是否正常
	 * @param userEntity 用户信息实体
	 * @param password 登录密码
	 * @return
	 * @throws UserAuthException
	 */
	private UserContextModel authAccount(UserEntity userEntity, String password) {
		
		if (userEntity != null) {
			//1、校验密码（若数据库中密码为空，则跳过）
			String pwdParam = Md5Util.MD5(password);
			String pwdDb = userEntity.getPassword();
			if (!StringUtils.isEmpty(pwdDb)) {
				if (!pwdDb.equals(pwdParam)) {
					throw new UserAuthException("用户名/密码校验不匹配.");
				}
			} else {//若数据库中密码为空，则报错。
				throw new UserAuthException("用户名/密码校验不匹配.");
			}
			//2、校验用户状态
			if (userEntity.getEnabled() != 1) {
				throw new UserAuthException("该用户已被停用，请联系管理员.");
			}
			//3、校验用户是否锁定
			if (userEntity.getLocked() != 0) {
				throw new UserAuthException("该用户已被锁定，请联系管理员.");
			}
			
			return toUserContextModel(userEntity);//返回用户信息（带角色、授权）
		} else {
			throw new UserAuthException("用户不存在.");
		}
	}

	/**
	 * 获取用户角色
	 * @param userEntity 用户实体文件信息
	 * @return 用户信息+角色
	 */
	private UserContextModel toUserContextModel(UserEntity userEntity) {
		
		UserContextModel model = new UserContextModel();
		BeanUtils.copyProperties(userEntity, model);
		//设置用户角色
		
		return model;
	}
}
