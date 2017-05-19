package com.weixin.honey.font.controller;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.honey.font.service.CategoryDetailService;
import com.weixin.honey.pojo.Girl;

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

	/**
	 * 跳到种类页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/listGirlCategory/{categoryId}")
	public String listGirlCategory(@PathVariable String categoryId,ModelMap modelMap){
		Set<Girl> girlsSet = null;
		try {
			girlsSet = (Set<Girl>) categoryDetailService.getCategoryGirlByCategoryId(categoryId);
			Iterator<Girl> git = girlsSet.iterator();
			while(git.hasNext()){
				git.next().getGirlId();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front/girlCategory";
	}
}
