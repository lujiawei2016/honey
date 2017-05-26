package com.weixin.honey.manager.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.manager.service.TokenService;
import com.weixin.honey.util.HttpUtils;
import com.weixin.honey.util.RedisUtils;

import net.sf.json.JSONObject;

/**
 * 微信tokenservice
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月26日上午11:41:18
 */
@Service
@Transactional
public class TokenServiceImpl implements TokenService {
	
	private static final Logger logger = Logger.getLogger(TokenServiceImpl.class);
	
	@Autowired
	private RedisUtils redisUtils;
	
	private static volatile String token = "";
	
	@Value("${appid}")
	private String appid;
	
	@Value("${appSecret}")
	private String appSecret;
	
	@Value("${weixinToken}")
	private String weixinToken;
	
	int num = 1;
	
	/**
	 * 获取redis的token
	 */
	@Override
	public String getToken() {
		token = (String) redisUtils.getValue(weixinToken);
		if(StringUtils.isBlank(token)){
			synchronized (TokenServiceImpl.class) {
				if(StringUtils.isBlank(token)){
					//微信token已经失效，需要重新获取
					logger.info("微信token已经失效，需要重新获取");
					String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appSecret+"";
					JSONObject json = HttpUtils.getMethodRequest(tokenUrl);
					token = json.optString("access_token", "");
					if("".equalsIgnoreCase(token) && num < 5){
						logger.info("第"+num+"次获取token失败，重新获取");
						num = num + 1;
						getToken();
					}else{
						num = 1;
						logger.info("token获取成功，放入到redis中");
						redisUtils.setValue(weixinToken, token, 100, TimeUnit.MINUTES);
					}
				}
			}
		}else{
			logger.info("微信token可以继续使用");
		}
		
		logger.info("token --> "+token);
		
		return token;
	}
	
}
