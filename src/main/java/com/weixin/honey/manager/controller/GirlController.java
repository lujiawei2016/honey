package com.weixin.honey.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.GirlService;
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
	
	@Autowired
	private GirlService girlService;

	/**
	 * 列出妹纸
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/listGirlPage")
	public String listGirlPage(ModelMap modelMap){
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
	public String update(Girl girl,String girlImgs){
		String result = null;
		try {
			result = (String) girlService.update(girl, girlImgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
