package com.weixin.honey.font.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信核心类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月26日上午11:00:32
 */
public interface WechatCoreService {

	/**
	 * 接收微信请求核心类
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request);
}
