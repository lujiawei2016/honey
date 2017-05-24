package com.weixin.honey.manager.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.honey.manager.service.GirlService;
import com.weixin.honey.pojo.Girl;

/**
 * 活动妹纸
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月24日下午3:01:48
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	private static final Logger logger = Logger.getLogger(ActivityController.class);
	
	@Autowired
	private GirlService girlService;

	/**
	 * 列出活动妹纸
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/listActivity")
	@RequiresPermissions("activity:listActivity")
	public String listActivity(ModelMap modelMap){
		List<Girl> girlList = null;
		try {
			girlList = (List<Girl>) girlService.findActiveGirlFromRedis(0, -1);
			if(girlList == null || girlList.size() == 0){
				logger.info("第一次获取活动妹纸失败，重新获取");
				girlList = (List<Girl>) girlService.findActiveGirlFromRedis(0, -1);
			}
			modelMap.put("girlList", girlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "manager/activity/activity";
	}
}
