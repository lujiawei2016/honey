package com.weixin.honey.manager.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.CategoryService;
import com.weixin.honey.pojo.Category;

/**
 * icon controller
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月13日下午9:17:55
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

	private static final Logger logger = Logger.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;

	/**
	 * 列出种类
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/listCategory")
	@RequiresPermissions("category:listIcon")
	public String listCategory(ModelMap modelMap){
		Object categoryList = new ArrayList<Object>();
		try {
			categoryList = categoryService.findAllCategory();
			modelMap.put("categoryList", categoryList);
		} catch (Exception e) {
			logger.error("取出图标异常");
			e.printStackTrace();
		}
		return "manager/category/category_list";
	}
	
	/**
	 * 跳转到新增或更新页面
	 * @return
	 */
	@RequestMapping(value="goUpdate")
	@RequiresPermissions("category:goUpdate")
	public String goUpdate(){
		return "manager/category/category_update";
	}
	
	/**
	 * 新增或者更新种类
	 * @param category
	 * @return
	 */
	@RequestMapping("/updateCategory")
	@RequiresPermissions("category:updateCategory")
	@ResponseBody
	public Object updateCategory(Category category){
		String result = "";
		try {
			result = (String) categoryService.updateCategory(category);
		} catch (Exception e) {
			logger.error("新增图标失败");
		}
		return result;
	}
	
	/**
	 * 删除种类图标
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/deleteCategory")
	@RequiresPermissions("category:categoryId")
	@ResponseBody
	public Object deleteCategory(String categoryId){
		Object result = "";
		try {
			result = categoryService.deleteCategory(categoryId);
		} catch (Exception e) {
			logger.error("删除种类图标异常");
		}
		return result;
	}
}
