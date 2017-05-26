package com.weixin.honey.font.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.font.service.WechatCoreService;
import com.weixin.honey.manager.service.TokenService;

/**
 * 微信核心类
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月23日下午7:42:31
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	@Autowired
	private WechatCoreService wechatCoreService;
	
	@Autowired
	private TokenService tokenService;

	/**
	 * 接收微信请求（用于对接微信）
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	/*@RequestMapping("/core")
	@ResponseBody
	public String core(String signature,String timestamp,String nonce,String echostr){
		 // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
        	System.out.println("微信请求校验通过，返回echostr");
        	return echostr;
        }else{
        	System.out.println("微信请求校验不通过");
        	return "00";
        }
	}*/
	
	@RequestMapping(value="/core",produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String core(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
        String respMessage = wechatCoreService.processRequest(request);  
          
        // 响应消息  
        return respMessage;
	}
	
}
