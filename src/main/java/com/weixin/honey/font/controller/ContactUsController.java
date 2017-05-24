package com.weixin.honey.font.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 联系我们controller
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月24日下午2:16:48
 */
@Controller
@RequestMapping("/contactUs")
public class ContactUsController {

	/**
	 * 跳转到联系我们页面
	 * @return
	 */
	@RequestMapping(value="/contact")
	public String contact(){
		return "front/contactUs";
	}
}
