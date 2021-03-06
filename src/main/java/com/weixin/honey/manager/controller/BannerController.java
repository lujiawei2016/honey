package com.weixin.honey.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.BannerService;
import com.weixin.honey.pojo.Banner;

/**
 * banner controller
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月25日下午2:47:09
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
	
	private static final Logger logger = Logger.getLogger(BannerController.class);
	
	@Autowired
	private BannerService bannerService;

	/**
	 * 列出banner
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/listBanner",produces="text/json;charset=UTF-8")
	@RequiresPermissions("banner:listBanner")
	public String listBanner(ModelMap modelMap){
		List<Banner> bannerList = new ArrayList<Banner>();
		try {
			bannerList = (List<Banner>) bannerService.findAllBanner();
		} catch (Exception e) {
			logger.error("列出banner异常");
			e.printStackTrace();
		}
		modelMap.put("bannerList", bannerList);
		return "manager/banner/banner_list";
	}
	
	/**
	 * 跳转到banner新增/编辑页面
	 * @param banner
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/goUpdate")
	@RequiresPermissions("banner:goUpdate")
	public String goUpdate(ModelMap modelMap){
		return "manager/banner/banner_update";
	}
	
	/**
	 * 新增或更新banner
	 * @param banner
	 * @return
	 */
	@RequestMapping("/updateBanner")
	@ResponseBody
	@RequiresPermissions("banner:updateBanner")
	public Object updateBanner(Banner banner){
		Object result = "";
		try {
			result = bannerService.updateBanner(banner);
		} catch (Exception e) {
			logger.error("新增或更新banner异常");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除banner
	 * @param bannerId
	 * @return
	 */
	@RequestMapping("/deleteBanner")
	@ResponseBody
	@RequiresPermissions("banner:deleteBanner")
	public Object deleteBanner(String bannerId){
		Object result = null;
		try {
			result = bannerService.deleteBanner(bannerId);
		} catch (Exception e) {
			logger.error("删除banner异常");
			e.printStackTrace();
		}
		return result;
	}
}
