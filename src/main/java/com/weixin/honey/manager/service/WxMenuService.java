package com.weixin.honey.manager.service;

import java.util.List;
import java.util.Map;

import com.weixin.honey.pojo.WxMenu;

/**
 * 微信菜单
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月26日上午11:34:33
 */
public interface WxMenuService {

	/**
	 * 保存微信菜单
	 * @param level
	 * @param erji
	 * @param fatherId
	 * @param type
	 * @param name
	 * @param url
	 * @param menuKey
	 * @param sort
	 */
	public void saveWxMenu(String level,String erji,String fatherId,String type,String name,String url,String menuKey,int sort);
	
	/**
	 * 查找微信菜单
	 * @return
	 */
	public Map<String, List<WxMenu>> findWxMenu();
	
	/**
	 * 创建微信菜单
	 * @return
	 */
	public boolean createWxMenu();
	
	/**
	 * 删除微信菜单
	 * @param id
	 * @return
	 */
	public String deleteWxMenu(int id);
	
}
