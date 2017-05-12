package com.weixin.honey.manager.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.LoginService;
import com.weixin.honey.util.EhcacheUtils;

/**
 * 用户controller
 * @author   lujiawei
 * @version  V1.0
 * @date     2017年4月15日下午10:37:04
 */
@Controller
@RequestMapping("/loginView")
public class LoginController {
	
	@Autowired
	private EhcacheUtils ehcacheUtils;
	
	@Autowired
	private LoginService loginService;
	
	@Value("sessionName")
	private String sessionName;

	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		Object obj = request.getSession().getAttribute(sessionName);
		if(obj == null){
			return "manager/login/login";
		}else{
			return "redirect:/manager/index/start";
		}
	}
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/loginIn")
	@ResponseBody
	public String loginIn(String username,String password,String verificationCode,HttpServletRequest request){
		try {
			return (String) loginService.loginIn(username, password, verificationCode,request);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 生成验证码
	 * @return
	 */
	@RequestMapping("/createVerificationCode")
	@ResponseBody
	public String createVerificationCode(){
		String uuid = UUID.randomUUID().toString();
		ehcacheUtils.setCache("verificationCodeCache", uuid, uuid);
		String jsonStr = "{\"uuid\":\""+uuid+"\"}";
		return jsonStr;
	}
	
	/**
	 * 退出登陆
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("sessionName");
		return "00";
	}
}
