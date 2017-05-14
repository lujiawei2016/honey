package com.weixin.honey.font.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.honey.font.service.BannerService;
import com.weixin.honey.font.service.CategoryService;
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
	
	@Autowired
	private CategoryService categoryService;

	/**
	 * 跳到主页
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/index",produces="text/json;charset=UTF-8")
	public String index(ModelMap modelMap){
		Object bannerList = null;
		Object categoryList = null;
		try {
			bannerList = bannerService.findAllBanner();
			categoryList = categoryService.findAllCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelMap.put("bannerList", bannerList);
		modelMap.put("categoryList", categoryList);
		
		return "front/index";
	}
}
