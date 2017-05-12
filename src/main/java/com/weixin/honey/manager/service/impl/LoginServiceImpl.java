package com.weixin.honey.manager.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.manager.service.LoginService;
import com.weixin.honey.util.EhcacheUtils;

/**
 * 登录service实现类
 * @author   lujiawei
 * @version  V1.0
 * @date     2017年4月16日下午2:48:21
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private EhcacheUtils ehcacheUtils;
	
	@Value("sessionName")
	private String sessionName;
	
	/**
	 * 登录方法
	 */
	@Override
	public Object loginIn(String username, String password, String verificationCode,HttpServletRequest request) throws Exception {
		if(!StringUtils.isBlank(username) && !StringUtils.isBlank(password) && !StringUtils.isBlank(verificationCode)){
			//用户名、密码、验证码都不为空
			String cacheVer = (String) ehcacheUtils.getCache("verificationCodeCache", verificationCode);//获取缓存验证码
			if(cacheVer != null && (cacheVer.equals(verificationCode))){
				//验证码正确
				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				try {
					subject.login(token);
				} catch (UnknownAccountException e) {
					//用户名不存在
					ehcacheUtils.removeCache("verificationCodeCache", verificationCode);
					return "3";
				} catch (IncorrectCredentialsException e){
					//密码错误
					ehcacheUtils.removeCache("verificationCodeCache", verificationCode);
					return "4";
				}
				
				//将对象放入到session
				request.getSession().setAttribute(sessionName, subject.getPrincipal());
				
				//移除缓存中的验证码
				ehcacheUtils.removeCache("verificationCodeCache", verificationCode);
				
				return "5";
				
			}else{
				//验证码错误
				ehcacheUtils.removeCache("verificationCodeCache", verificationCode);
				return "2";
			}
		}else{
			//用户名、密码、验证码都为空
			return "1";
		}
	}

}
