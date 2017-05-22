package com.weixin.honey.font.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private UserLoginService userLoginService;

	/**
	 * 跳到登录页面
	 * @return
	 */
	@RequestMapping(value="/loginGirl")
	public String loginGirl(HttpServletRequest request,ModelMap modelMap){
		String referer = request.getHeader("referer"); //获得上次的访问路径
		modelMap.put("referer", referer);
		return "front/login";
	}
	
	/**
	 * 跳到注册页面
	 * @return
	 */
	@RequestMapping(value="/registerUser")
	public String registerUser(){
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
			e.printStackTrace();
		}
		return result;
	}
}
