package com.weixin.honey.font.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.font.service.UserLoginService;

/**
 * 前台用户登录界面
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月21日下午2:01:45
 */
@Controller
@RequestMapping("/userLogin")
public class UserLoginController {
	
	private static final Logger logger = Logger.getLogger(UserLoginController.class);
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Value("user")
	private String userSession;

	/**
	 * 跳到登录页面
	 * @return
	 */
	@RequestMapping(value="/loginGirl")
	public String loginGirl(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		Object user = request.getSession().getAttribute(userSession);
		if(user != null){
			try {
				response.sendRedirect(request.getContextPath()+"/main/index");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			String referer = request.getHeader("referer"); //获得上次的访问路径
			if(StringUtils.isBlank(referer) || referer.contains("registerUser") || referer.contains("loginGirl")){
				modelMap.put("referer", "");
			}else{
				modelMap.put("referer", referer);
			}
		}
		return "front/login";
	}
	
	/**
	 * 跳到注册页面
	 * @return
	 */
	@RequestMapping(value="/registerUser")
	public String registerUser(HttpServletResponse response,HttpServletRequest request){
		Object user = request.getSession().getAttribute(userSession);
		if(user != null){
			try {
				response.sendRedirect(request.getContextPath()+"/main/index");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "front/register";
	}

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/loginInGirl/{username}/{password}")
	@ResponseBody
	public Object loginInGirl(@PathVariable String username,@PathVariable String password,HttpServletRequest request){
		Object result = null;
		try {
			result = userLoginService.loginInGirl(request, username, password);
		} catch (Exception e) {
			logger.error("跳到登陆页面异常");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/registerIn/{username}/{password}")
	@ResponseBody
	public Object registerIn(@PathVariable String username,@PathVariable String password,HttpServletRequest request){
		String result = null;
		try {
			result = (String) userLoginService.registerIn(request,username, password);
		} catch (Exception e) {
			logger.error("用户注册异常");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 退出账号
	 * @param request
	 * @return
	 */
	@RequestMapping("/exitUser")
	public Object exitUser(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute(userSession);
		return "redirect:/main/index";
	}
}
