package com.weixin.honey.font.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.honey.manager.service.GirlService;
import com.weixin.honey.pojo.Girl;

/**
 * VIP controller
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月20日下午10:23:32
 */
@Controller
@RequestMapping("/vip")
public class VipController {
	
	private static final Logger logger = Logger.getLogger(VipController.class);
	
	@Autowired
	private GirlService girlService;

	/**
	 * 列出所有VIP妹纸
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/listVip")
	public String listVip(ModelMap modelMap){
		List<Girl> girlList = null;
		try {
			girlList = (List<Girl>) girlService.findVipGirlFromRedis(0, -1);
			if(girlList == null || girlList.size() == 0){
				logger.info("第一次获取VIP妹纸失败，重新获取");
				girlList = (List<Girl>) girlService.findVipGirlFromRedis(0, -1);
			}
			modelMap.put("girlList", girlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front/vip";
	}
}
