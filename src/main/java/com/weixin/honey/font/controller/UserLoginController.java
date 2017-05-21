package com.weixin.honey.font.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * 前台用户登录界面
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月21日下午2:01:45
 */
@Controller
@RequestMapping("/userLogin")
public class UserLoginController {

	/**
	 * 跳到登录页面
	 * @return
	 */
	@RequestMapping(value="/loginGirl")
	public String loginGirl(){
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
}
