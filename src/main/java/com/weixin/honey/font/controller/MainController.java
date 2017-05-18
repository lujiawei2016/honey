package com.weixin.honey.font.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.BannerService;
import com.weixin.honey.manager.service.CategoryService;
import com.weixin.honey.manager.service.GirlService;
import com.weixin.honey.pojo.Girl;

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
	
	/**
	 * 加载妹纸
	 * @param start 从start位置开始加载
	 * @return
	 */
	@RequestMapping(value="/loadGirl",method=RequestMethod.POST)
	@ResponseBody
	public Object loadGirl(String start){
		List<Girl> girlList = null;
		if(!StringUtils.isBlank(start) && StringUtils.isNumeric(start)){
			try {
				girlList = (List<Girl>) girlService.findGirlFromRedis(Integer.parseInt(start), Integer.parseInt(start)+9);
				if(girlList == null || girlList.size() == 0){
					girlList = (List<Girl>) girlService.findGirlFromRedis(Integer.parseInt(start), Integer.parseInt(start)+9);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return girlList;
	}
}
