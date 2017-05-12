package com.weixin.honey.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台登陆session过滤器
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月18日上午11:45:31
 */
public class SessionInterceptor implements HandlerInterceptor {
	
	@Value("sessionName")
	private String sessionName;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object obj = request.getSession(true).getAttribute(sessionName);
		
		if(obj == null){
			//登陆过期
			
			String headerX = request.getHeader("X-Requested-With");
			if(headerX != null && "XMLHttpRequest".equals(headerX)){
				response.getWriter().write("ajaxIsTimeOut");
				return false;
			}else{
				response.sendRedirect(request.getContextPath()+"/loginView/login");
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
