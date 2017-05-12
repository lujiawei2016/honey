package com.weixin.honey.manager.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.PermissionService;
import com.weixin.honey.pojo.Permission;

/**
 * 权限controller
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月17日下午1:56:49
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	private PermissionService permissinoService;

	/**
	 * 列出权限
	 * @param name    权限名称
	 * @param pageNum 页码
	 * @param length  一页展示条数
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/listPermission")
	@RequiresPermissions("permission:listPermission")
	public String listPermission(String name,String pageNum,String length,ModelMap modelMap){
		try {
			Object permissionData = permissinoService.findPagePermission(name, pageNum, length);
			Object fatherPermissionList = permissinoService.findFatherPermission();  //查找父权限
			
			modelMap.put("permissionData", permissionData);
			modelMap.put("fatherPermissionList", fatherPermissionList);
			modelMap.put("currentPage", pageNum==null?"1":pageNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/permission/permission_list";
	}
	
	/**
	 * 保存或者更新权限
	 * @param type
	 * @param group
	 * @param permission
	 * @return
	 */
	@RequestMapping("/updatePermission")
	@RequiresPermissions("permission:updatePermission")
	@ResponseBody
	public String updatePermission(String type,String father_id,Permission permission){
		Object result = "";
		try {
			result = permissinoService.updatePermission(type, father_id, permission);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	/**
	 * 删除权限
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePermission")
	@RequiresPermissions("permission:deletePermission")
	@ResponseBody
	public String deletePermission(String id){
		Object obj = null;
		try {
			obj = permissinoService.deletePermissionById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
}
