package com.heibaba.authorize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.heibaba.authorize.component.AuthAccount;
import com.heibaba.authorize.component.UserScopeCreator;
import com.heibaba.authorize.dto.UserScopeDto;
import com.heibaba.authorize.model.UserContextModel;
import com.heibaba.common.exception.UserAuthException;
import com.heibaba.common.utils.UserNameType;
import com.heibaba.common.utils.UserNameUtil;

/**
 * 用户身份认证服务类
 * @author shenhl
 *
 */
@Service
@Transactional
public class AuthService {
	
	@Autowired
	private UserScopeCreator usc;

	/**
	 * 校验用户名，密码及用户域的合法性
	 * @param accountName
	 * @param password
	 * @param scope
	 * @return
	 * @throws UserAuthException
	 */
	public UserContextModel auth(String accountName, String password, String scope) {

		UserContextModel userModel = null;
		
		if (StringUtils.isEmpty(accountName)) {
			throw new UserAuthException("权限校验失败。原因：输入用户账户名为空。");
		}
		if (StringUtils.isEmpty(password)) {
			throw new UserAuthException("权限校验失败。原因：输入密码为空。");
		}
		if (StringUtils.isEmpty(scope)) {
			throw new UserAuthException("权限校验失败。原因：用户域为空。");
		}
		
		UserScopeDto userScope = usc.userScope(scope);//用户类型 admin or  web
		if (userScope == null) {
			throw new UserAuthException("权限校验失败。原因：用户域["+scope+"]不存在。");
		}
		
		UserNameType type = UserNameUtil.type(accountName);
		AuthAccount authImpl = userScope.getAuthAccountImpl();
		switch (type) {
			case USERNAME:
				userModel = authImpl.userName(accountName, password);//用户名
				break;
			case TELEPHONE:
				userModel = authImpl.telephone(accountName, password);//手机号
				break;
			case NONE:
				throw new UserAuthException("用户账户名格式错误。");
		}
		
		return userModel;
	}
}
