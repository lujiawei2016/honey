package com.weixin.honey.font.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端用户登录或注册
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月22日上午10:38:39
 */
public interface UserLoginService {
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Object loginInGirl(HttpServletRequest request,String username,String password) throws Exception;

	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @return
	 */
	public Object registerIn(HttpServletRequest request,String username,String password) throws Exception;
}
