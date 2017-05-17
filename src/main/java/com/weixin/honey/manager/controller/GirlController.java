package com.weixin.honey.manager.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.CategoryService;
import com.weixin.honey.manager.service.GirlService;
import com.weixin.honey.pojo.Category;
import com.weixin.honey.pojo.Girl;

/**
 * 妹纸controller
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月15日下午11:23:33
 */
@Controller
@RequestMapping("/girl")
public class GirlController {
	
	private static final Logger logger = Logger.getLogger(GirlController.class);
	
	@Autowired
	private GirlService girlService;
	
	@Autowired
	private CategoryService categoryService;

	/**
	 * 列出妹纸
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/listGirlPage")
	public String listGirlPage(ModelMap modelMap,Girl girl){
		Object girlList = null;
		try {
			girlList = girlService.findGirlFromDatabase(girl);
			modelMap.put("girlList", girlList);
		} catch (Exception e) {
			logger.error("列出妹纸异常");
			e.printStackTrace();
		}
		return "manager/girl/girl_list";
	}
	
	/**
	 * 跳转到添加或者编辑妹纸的页面
	 * @param girlId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/goUpdate")
	public String goUpdate(String girlId,ModelMap modelMap){
		Object girl = null;
		Object categoryList = null;
		try {
			girl = girlService.findGirlById(girlId);
			categoryList = categoryService.findAllCategory();
			
			modelMap.put("girl", girl);
			modelMap.put("categoryList", categoryList);
		} catch (Exception e) {
			logger.error("跳转到添加或者编辑妹纸的页面异常");
			e.printStackTrace();
		}
		return "manager/girl/girl_update";
	}
	
	/**
	 * 保存或者更新妹纸
	 * @param girl
	 * @param girlImgs
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String update(Girl girl,String girlImgs,String categorys){
		String result = null;
		try {
			result = (String) girlService.update(girl, girlImgs,categorys);
		} catch (Exception e) {
			logger.error("新增或编辑妹纸异常");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id删除图片
	 * @param girlImgId
	 * @return
	 */
	@RequestMapping("/deleteGirlImgsById")
	@ResponseBody
	public String deleteGirlImgsById(String girlImgId){
		String result = null;
		try {
			result = (String) girlService.deleteGirlImgsById(girlImgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
