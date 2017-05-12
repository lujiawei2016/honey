package com.weixin.honey.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页controller
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月17日上午9:03:12
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	/**
	 * 调到start.jsp页面
	 * @return
	 */
	@RequestMapping("/start")
	public String start(){
		return "manager/start/start";
	}
	
	/**
	 * 拒绝页面
	 * @return
	 */
	@RequestMapping("/refuse")
	public String refuse(){
		return "manager/refuse/refuse";
	}
	
}
