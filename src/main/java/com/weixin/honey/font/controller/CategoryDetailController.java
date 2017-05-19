package com.weixin.honey.font.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.font.service.CategoryDetailService;
import com.weixin.honey.manager.service.CategoryService;

/**
 * 跳到种类详情页
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月19日下午4:09:50
 */
@Controller
@RequestMapping("/categoryDetail")
public class CategoryDetailController {
	
	@Autowired
	private CategoryDetailService categoryDetailService;
	
	@Autowired
	private CategoryService categoryService;

	/**
	 * 跳到种类页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/listGirlCategory/{categoryId}/{start}/{end}")
	public String listGirlCategory(@PathVariable String categoryId,@PathVariable long start,@PathVariable long end,ModelMap modelMap){
		Object categoryDetailList = null;
		Object category = null;
		try {
			categoryDetailList = categoryDetailService.getCategoryGirlByCategoryId(categoryId,start,end);
			category = categoryService.findCategoryById(categoryId);
			
			modelMap.put("categoryDetailList", categoryDetailList);
			modelMap.put("category", category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front/girlCategory";
	}
	
	/**
	 * ajax加载页面
	 * @param categoryId
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value="/loadGirlCategory/{categoryId}/{start}/{end}")
	@ResponseBody
	public Object loadGirlCategory(@PathVariable String categoryId,@PathVariable long start,@PathVariable long end){
		Object categoryDetailList = null;
		try {
			categoryDetailList = categoryDetailService.getCategoryGirlByCategoryId(categoryId,start,end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryDetailList;
	}
}
