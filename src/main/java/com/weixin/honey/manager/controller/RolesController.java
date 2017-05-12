package com.weixin.honey.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.RolesService;
import com.weixin.honey.pojo.Roles;

/**
 * 角色controller
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月17日下午1:48:38
 */
@Controller
@RequestMapping("/roles")
public class RolesController {
	
	@Autowired
	private RolesService rolesService;

	/**
	 * 列出所有角色
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/listRoles")
	@RequiresPermissions("roles:listRoles")
	public String listRoles(String rolesName,String pageNum,String length,ModelMap modelMap){
		Object fatherList = null;
		Object roleList = null;
		try {
			fatherList = rolesService.findFatherRoles();
			roleList =  rolesService.findPageRoles(rolesName, pageNum, length);
			modelMap.put("fatherList", fatherList);
			modelMap.put("roleList", roleList);
			modelMap.put("currentPage", pageNum==null?"1":pageNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/roles/roles_list";
	}
	
	/**
	 * 根据id获取权限树
	 * @param id
	 * @return
	 */
	@RequestMapping("/getPermissionTreeByRolesId")
	@RequiresPermissions("roles:getPermissionTreeByRolesId")
	@ResponseBody
	public Object getPermissionTreeByRolesId(String rolesId){
		Object obj = null;
		try {
			obj = rolesService.getPermissionTreeByRolesId(rolesId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 保存或者更新角色
	 * @param type
	 * @param name
	 * @param father_id
	 * @param rolesId
	 * @param treeId
	 * @return
	 */
	@RequestMapping("/updateRoles")
	@RequiresPermissions("roles:updateRoles")
	@ResponseBody
	public Object updateRoles(String type,String name,String father_id,String rolesId,String treeId){
		Object obj = null;
		try {
			obj = rolesService.updateRoles(type, name, father_id, rolesId, treeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 编辑角色
	 * @param rolesId
	 * @return
	 */
	@RequestMapping("/editRoles")
	@RequiresPermissions("roles:editRoles")
	@ResponseBody
	public Object editRoles(String rolesId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Roles roles = (Roles) rolesService.findRolesById(rolesId);
			
			if(roles.getFather() != null){
				map.put("father_id", roles.getFather().getId());
			}else{
				map.put("father_id", null);
			}
			
			map.put("roles", roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据id删除角色
	 * @param rolesId
	 * @return
	 */
	@RequestMapping("/deleteRolesById")
	@RequiresPermissions("roles:deleteRolesById")
	@ResponseBody
	public Object deleteRolesById(String rolesId){
		Object obj = null;
		try {
			obj = rolesService.deleteRolesById(rolesId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}
