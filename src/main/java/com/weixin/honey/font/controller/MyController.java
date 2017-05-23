package com.weixin.honey.font.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 个人controller
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月23日下午2:38:53
 */
@Controller
@RequestMapping("/my")
public class MyController {

	/**
	 * 跳转到个人页面
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping("/myInfo")
	public String myInfo(ModelMap modelMap,HttpServletRequest request){
		return "front/my";
	}
}
