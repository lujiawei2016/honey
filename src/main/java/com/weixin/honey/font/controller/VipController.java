package com.weixin.honey.font.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.honey.manager.service.GirlService;

/**
 * VIP controller
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月20日下午10:23:32
 */
@Controller
@RequestMapping("/vip")
public class VipController {
	
	@Autowired
	private GirlService girlService;

	/**
	 * 列出所有VIP妹纸
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/listVip")
	public String listVip(ModelMap modelMap){
		Object girlList = null;
		try {
			girlList = girlService.findVipGirlFromRedis(0, -1);
			modelMap.put("girlList", girlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front/vip";
	}
}
