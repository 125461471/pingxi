package com.heibaba;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;


@Configuration
public class DruidConfig {

	@Bean
    public ServletRegistrationBean druidServlet() {
		
//        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.setUrlMappings(Arrays.asList("/druid/*"));
//		servletRegistrationBean.addInitParameter("allow", "192.168.16.110,127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
//		servletRegistrationBean.addInitParameter("deny", "192.168.16.111/24");// IP黑名单 (存在共同时，deny优先于allow)
		servletRegistrationBean.addInitParameter("loginUsername", "shl");// 用户名
		servletRegistrationBean.addInitParameter("loginPassword", "shl");// 密码
		servletRegistrationBean.addInitParameter("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能(经测试，按钮还有，不过功能屏蔽了)
		return servletRegistrationBean;
    }
	
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
