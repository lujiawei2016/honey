package com.weixin.honey.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前台登录过滤器
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月21日下午2:07:21
 */
public class FontInterceptor implements HandlerInterceptor {
	
	@Value("${userSession}")
	private String userSession;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object obj = request.getSession(true).getAttribute(userSession);
		
		if(obj == null){
			//登陆过期
			String headerX = request.getHeader("X-Requested-With");
			if(headerX != null && "XMLHttpRequest".equals(headerX)){
				response.getWriter().write("ajaxIsTimeOut");
				return false;
			}else{
				response.sendRedirect(request.getContextPath()+"/userLogin/loginGirl");
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
