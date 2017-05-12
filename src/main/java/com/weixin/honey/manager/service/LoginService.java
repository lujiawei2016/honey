package com.weixin.honey.manager.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录service
 * @author   lujiawei
 * @version  V1.0
 * @date     2017年4月16日下午2:47:01
 */
public interface LoginService {

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param verificationCode
	 * @return
	 * @throws Exception
	 */
	public Object loginIn(String username,String password,String verificationCode,HttpServletRequest request) throws Exception;
}
