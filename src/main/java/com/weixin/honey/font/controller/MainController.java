package com.weixin.honey.font.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.honey.manager.service.BannerService;
import com.weixin.honey.manager.service.CategoryService;
import com.weixin.honey.manager.service.GirlService;
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
	
	@Autowired
	private GirlService girlService;

	/**
	 * 跳到主页
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/index",produces="text/json;charset=UTF-8")
	public String index(ModelMap modelMap){
		Object bannerList = null;      // 轮播图
		Object categoryList = null;    // 种类
		Object girlList = null;        // 妹纸
		try {
			bannerList = bannerService.findAllBanner();
			categoryList = categoryService.findAllCategory();
			girlList = girlService.findGirlFromRedis(0, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelMap.put("bannerList", bannerList);
		modelMap.put("categoryList", categoryList);
		modelMap.put("girlList", girlList);
		
		return "front/index";
	}
}
