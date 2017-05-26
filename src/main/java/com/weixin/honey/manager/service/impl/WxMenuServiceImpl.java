package com.weixin.honey.manager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.manager.service.TokenService;
import com.weixin.honey.manager.service.WxMenuService;
import com.weixin.honey.pojo.WxMenu;

import net.sf.json.JSONObject;


/**
 * 微信菜单
 * @author lujiawei
 *
 */
@Service
@Transactional
public class WxMenuServiceImpl implements WxMenuService {
	
	@Autowired
	private BaseDao<WxMenu, Integer> wxMenuDao;
	
	@Autowired
	private TokenService tokenService;
	
	private static final Logger logger = Logger.getLogger(WxMenuServiceImpl.class);

	/**
	 * 保存微信菜单
	 */
	@Override
	public void saveWxMenu(String level, String erji, String fatherId,
			String type, String name, String url, String menuKey, int sort) {
		
		WxMenu wxMenu =  new WxMenu();
		
		if("1".equals(level) || "1" == level){
			//一级菜单
			if("1".equals(erji) || "1" == erji){
				//一级菜单有二级菜单
				wxMenu.setName(name);
				wxMenu.setFatherId(0);
				wxMenu.setMark(1);
				wxMenu.setSort(sort);
			}else if("0".equals(erji) || "0" == erji){
				//一级菜单无二级菜单
				if("click".equals(type) || "click" == type){
					//一级菜单为click类型
					wxMenu.setName(name);
					wxMenu.setFatherId(0);
					wxMenu.setMark(0);
					wxMenu.setMenuKey(menuKey);
					wxMenu.setSort(sort);
					wxMenu.setType(type);
				}else if("view".equals(type) || "view" == type){
					//一级菜单为view类型
					wxMenu.setName(name);
					wxMenu.setFatherId(0);
					wxMenu.setMark(0);
					wxMenu.setUrl(url);
					wxMenu.setSort(sort);
					wxMenu.setType(type);
				}
			}else{
				//未知错误
				logger.info("一级菜单选择错误....");
			}
		}else if("2".equals(level) || "2" == level){
			//二级菜单
			WxMenu fatherMenu = wxMenuDao.findById(WxMenu.class, Integer.parseInt(fatherId));
			if("click".equals(type) || "click" == type){
				wxMenu.setFather(fatherMenu);
				wxMenu.setMenuKey(menuKey);
				wxMenu.setName(name);
				wxMenu.setSort(sort);
				wxMenu.setType(type);
				wxMenu.setFatherId(Integer.parseInt(fatherId));
			}else if("view".equals(type) || "view" == type){
				wxMenu.setFather(fatherMenu);
				wxMenu.setUrl(url);
				wxMenu.setName(name);
				wxMenu.setSort(sort);
				wxMenu.setType(type);
				wxMenu.setFatherId(Integer.parseInt(fatherId));
			}else{
				logger.info("二级菜单级别选择错误....");
			}
		}else{
			//未知菜单
			logger.info("一级菜单级别选择错误.....");
		}
		wxMenuDao.save(wxMenu);
		logger.info("保存微信菜单成功.....");
	}

	/**
	 * 查出父菜单和子菜单
	 */
	@Override
	public Map<String, List<WxMenu>> findWxMenu() {
		Map<String, List<WxMenu>> menuMap = new HashMap<String, List<WxMenu>>();
		List<WxMenu> fahterMenuList = wxMenuDao.findByHql("from WxMenu where fatherId=0 order by sort asc"); //查出所有父菜单
		List<WxMenu> childrenMenuList = wxMenuDao.findByHql("from WxMenu where fatherId!=0 order by sort asc");//查出所有子菜单
		menuMap.put("fahterMenuList", fahterMenuList);
		menuMap.put("childrenMenuList", childrenMenuList);
		return menuMap;
	}

	/**
	 * 生成微信菜单
	 */
	@Override
	public boolean createWxMenu() {
		String token = tokenService.getToken();
		String createWxMenuUrl = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token+"";
		boolean b = false;
		
		List<WxMenu> wxFatherMenuList = wxMenuDao.findByHql("from WxMenu where fatherId=0 order by sort asc"); //查出所有父菜单
		List<WxMenu> wxChildrenMenuList = wxMenuDao.findByHql("from WxMenu where fatherId!=0 order by sort asc");//查出所有子菜单
		List<String> allWxMenu = new ArrayList<String>();
		String oneMenu = null; //用来装单个菜单
		String manyChildrenMenu = ""; //用来装多个子菜单
		String wxMenu = ""; //最终微信菜单模样
		
		for(WxMenu fatherMenu:wxFatherMenuList){
			manyChildrenMenu = "";
			if(fatherMenu.getMark() == 0){
				//一级菜单没有二级菜单
				if("click".equals(fatherMenu.getType())){
					oneMenu = "{\"type\":\"click\",\"name\":\""+fatherMenu.getName()+"\",\"key\":\""+fatherMenu.getMenuKey()+"\"}";
				}else if("view".equals(fatherMenu.getType())){
					oneMenu = "{\"type\":\"view\",\"name\":\""+fatherMenu.getName()+"\",\"url\":\""+fatherMenu.getUrl()+"\"}";
				}else{
					logger.info("生成一级菜单出错....");
				}
				allWxMenu.add(oneMenu);
			}else if(fatherMenu.getMark() == 1){
				//一级菜单有二级菜单
				for(WxMenu childrenMenu:wxChildrenMenuList){
					if(fatherMenu.getId() == childrenMenu.getFatherId()){
						if("click".equals(childrenMenu.getType())){
							manyChildrenMenu = manyChildrenMenu + "{\"type\":\"click\",\"name\":\""+childrenMenu.getName()+"\",\"key\":\""+childrenMenu.getMenuKey()+"\"}";
						}else if("view".equals(childrenMenu.getType())){
							manyChildrenMenu = manyChildrenMenu + "{\"type\":\"view\",\"name\":\""+childrenMenu.getName()+"\",\"url\":\""+childrenMenu.getUrl()+"\"}";
						}else{
							logger.info("生成二级菜单出错....");
						}
					}
				}
				allWxMenu.add("{\"name\":\""+fatherMenu.getName()+"\",\"sub_button\":["+manyChildrenMenu+"]}");
			}
		}
		
		for(String s : allWxMenu){
			wxMenu = wxMenu+s;
		}
		wxMenu = wxMenu.replace("}{", "},{");
		wxMenu = "{\"button\":["+wxMenu+"]}";
		logger.info("生成微信菜单:"+wxMenu);
		logger.info("正在创建微信菜单....");
		String json = com.weixin.honey.util.HttpUtils.postMethodRequest(createWxMenuUrl, wxMenu);
		JSONObject jsonObject = JSONObject.fromObject(json);
		if((jsonObject.getInt("errcode")) == 0){
			b = true;
			logger.info("微信菜单创建成功");
		}else{
			logger.info("微信菜单创建失败:"+json);
		}
		return b;
	}

	/**
	 * 删除微信菜单
	 */
	@Override
	public String deleteWxMenu(int id) {
		String result = null;
		WxMenu wxMenu = wxMenuDao.findById(WxMenu.class, id);
		if(wxMenu != null){
			wxMenuDao.deleteById(WxMenu.class, id);
			result = "2";
		}else{
			result = "3";
		}
		return result;
	}
 
}
