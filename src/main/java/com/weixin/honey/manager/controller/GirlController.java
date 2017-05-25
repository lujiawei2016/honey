package com.weixin.honey.manager.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.CategoryService;
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
	@RequiresPermissions("girl:listGirlPage")
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
	 * 列出普通redis中的妹纸
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/listRedisGirl")
	@RequiresPermissions("girl:listRedisGirl")
	public String listRedisGirl(ModelMap modelMap){
		List<Girl> girlList = null;
		try {
			girlList = (List<Girl>) girlService.findGirlFromRedis(0, -1);
			if(girlList == null || girlList.size() == 0){
				logger.info("第一次从redis中获取妹纸失败，再次获取");
				girlList = (List<Girl>) girlService.findGirlFromRedis(0, -1);
			}
			modelMap.put("girlList", girlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/girl/girl_redis_list";
	}
	
	/**
	 * 列出VIP redis中的妹纸
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/listVIPRedisGirl")
	@RequiresPermissions("girl:listVIPRedisGirl")
	public String listVIPRedisGirl(ModelMap modelMap){
		List<Girl> girlList = null;
		try {
			girlList = (List<Girl>) girlService.findVipGirlFromRedis(0, -1);
			if(girlList == null || girlList.size() == 0){
				logger.info("第一次从redis中获VIP取妹纸失败，再次获取");
				girlList = (List<Girl>) girlService.findVipGirlFromRedis(0, -1);
			}
			modelMap.put("girlList", girlList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/girl/girl_redis_list";
	}
	
	/**
	 * 跳转到添加或者编辑妹纸的页面
	 * @param girlId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/goUpdate")
	@RequiresPermissions("girl:goUpdate")
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
	@RequiresPermissions("girl:update")
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
	@RequiresPermissions("girl:deleteGirlImgsById")
	@ResponseBody
	public String deleteGirlImgsById(String girlImgId){
		String result = null;
		try {
			result = (String) girlService.deleteGirlImgsById(girlImgId);
		} catch (Exception e) {
			logger.error("根据id删除图片异常");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id删除妹纸
	 * @param girlId
	 * @return
	 */
	@RequestMapping("/deleteOrLifelById")
	@RequiresPermissions("girl:deleteOrLifelById")
	@ResponseBody
	public String deleteOrLifelById(String girlId){
		String result = "";
		try {
			result = (String) girlService.deleteOrLifelById(girlId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
