package com.weixin.honey.font.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.honey.font.service.DetailService;

/**
 * 详情controller
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月19日上午9:57:54
 */
@Controller
@RequestMapping("/detail")
public class DetailController {
	
	private static final Logger logger = Logger.getLogger(DetailController.class);
	
	@Autowired
	private DetailService detailService;

	/**
	 * 跳妹纸详情页面
	 * @param girlId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/girlDetail/{girlId}")
	public String girlDetail(@PathVariable String girlId,ModelMap modelMap){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			dataMap = (Map<String, Object>) detailService.getGirl(girlId);
			if(dataMap != null && dataMap.size() != 0){
				modelMap.put("dataMap", dataMap);
			}else{
				modelMap.put("dataMap", null);
			}
		} catch (Exception e) {
			logger.error("跳妹纸详情页面异常");
			e.printStackTrace();
		}
		return "front/girlDetail";
	}
	
}
