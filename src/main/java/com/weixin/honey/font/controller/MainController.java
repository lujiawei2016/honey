package com.weixin.honey.font.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.honey.font.service.BannerService;
import com.weixin.honey.pojo.Banner;

/**
 * 前台主页
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月25日上午10:09:18
 */
@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private BannerService bannerService;

	/**
	 * 跳到主页
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/index",produces="text/json;charset=UTF-8")
	public String index(ModelMap modelMap){
		List<Banner> bannerList = new ArrayList<>();
		try {
			bannerList = (List<Banner>) bannerService.findAllBanner();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelMap.put("bannerList", bannerList);
		
		return "front/index";
	}
}
