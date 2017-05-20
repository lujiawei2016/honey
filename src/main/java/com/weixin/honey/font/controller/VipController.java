package com.weixin.honey.font.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * VIP controller
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月20日下午10:23:32
 */
@Controller
@RequestMapping("/vip")
public class VipController {

	@RequestMapping(value="/listVip")
	public String listVip(ModelMap modelMap){
		return "front/vip";
	}
}
