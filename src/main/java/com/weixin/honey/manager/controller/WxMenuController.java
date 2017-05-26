package com.weixin.honey.manager.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.WxMenuService;
import com.weixin.honey.pojo.WxMenu;


/**
 * 微信菜单
 * @author luiawei
 *
 */
@Controller
@RequestMapping("/wxMenu")
public class WxMenuController {
	
	@Autowired
	private WxMenuService wxMenuService;

	/**
	 * 查出微信菜单
	 */
	@RequestMapping("/listMenu")
	@RequiresPermissions("wxMenu:listMenu")
	public String wxMenu(ModelMap modelMap){
		Map<String, List<WxMenu>> menuMap = wxMenuService.findWxMenu();
		List<WxMenu> wxFahterMenuList = menuMap.get("fahterMenuList");
		List<WxMenu> WxChildrenMenuList = menuMap.get("childrenMenuList");
		modelMap.put("wxFahterMenuList", wxFahterMenuList);
		modelMap.put("WxChildrenMenuList", WxChildrenMenuList);
		return "manager/wxMenu/wxMenu";
	}
	
	/**
	 * 保存微信菜单
	 */
	@RequestMapping("/saveWxMenu")
	@RequiresPermissions("wxMenu:saveWxMenu")
	@ResponseBody
	public String saveWxMenu(String level,String erji,String fatherId,String type,String name,String url,String menuKey,int sort){
		wxMenuService.saveWxMenu(level, erji, fatherId, type, name, url, menuKey, sort);
		return "0";
	}
	
	/**
	 * 创建微信菜单
	 */
	@RequestMapping("/createWxMenu")
	@RequiresPermissions("wxMenu:createWxMenu")
	@ResponseBody
	public String createWxMenu(){
		boolean b = wxMenuService.createWxMenu();
		String result = "";
		if(b){
			result = "0";
		}else{
			result = "1";
		}
		return result;
	}
	
	/**
	 * 删除微信菜单
	 */
	@RequestMapping("/deleteWxMenu")
	@RequiresPermissions("wxMenu:deleteWxMenu")
	@ResponseBody
	public String deleteWxMenu(int id){
		String result = wxMenuService.deleteWxMenu(id);
		return result;
	}
	
}
