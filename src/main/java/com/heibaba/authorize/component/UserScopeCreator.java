package com.heibaba.authorize.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.heibaba.authorize.dto.UserScopeDto;

/**
 * 用户域创建类
 * @author shenhl
 *
 */
@Component
public class UserScopeCreator implements InitializingBean, ApplicationContextAware {

		private Map<String, UserScopeDto> map;
		private ApplicationContext context;

		/**
		 * 网站用户域
		 * @return
		 */
		public UserScopeDto createWeb() {
			
			UserScopeDto option = new UserScopeDto();
			option.setScope("web");
			option.setName("互联网用户");
			option.setGroup("web@");
			AuthAccount bean = context.getBean(AuthWebAccountImpl.SERVICE_NAME, AuthAccount.class);
			option.setAuthAccountImpl(bean);
			return option;
		}

		/**
		 * 管理员用户域
		 * @return
		 */
		public UserScopeDto createAdmin() {
			
			UserScopeDto option = new UserScopeDto();
			option.setScope("shladmin");
			option.setName("管理员用户");
			option.setGroup("shladmin@");
			AuthAccount bean = context.getBean(AuthAdminAccountImpl.SERVICE_NAME, AuthAccount.class);
			option.setAuthAccountImpl(bean);
			return option;
		}
		
		@Override
		public void afterPropertiesSet() {
			
			map = new ConcurrentHashMap<String, UserScopeDto>();
			//互联网用户
			UserScopeDto web = createWeb();
			map.put(web.getScope(), web);
			//管理员用户
			UserScopeDto admin = createAdmin();
			map.put(admin.getScope(), admin);
		}
		
		public UserScopeDto userScope(String scope) {
			
			return map.get(scope);
		}
		
		@Override
		public void setApplicationContext(ApplicationContext context){
			
			this.context = context;
		}

	}
