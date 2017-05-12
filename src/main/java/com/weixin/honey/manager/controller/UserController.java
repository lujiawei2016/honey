package com.weixin.honey.manager.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.manager.service.UserService;
import com.weixin.honey.pojo.User;

/**
 * 用户controller
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月17日上午9:20:14
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * 列出用户
	 * @param user
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/listUser")
	@RequiresPermissions("user:listUser")
	public String listUser(User user,String pageNum,String length,ModelMap modelMap){
		try {
			Object obj = userService.findPageUser(user, pageNum, length);
			modelMap.put("obj", obj);
			modelMap.put("currentPage", pageNum==null?"1":pageNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/user/user_list";
	}
	
	/**
	 * 跳转到新增或者编辑页面
	 * @param userId
	 * @return
	 */
	@RequestMapping("/goUpdate")
	@RequiresPermissions("user:goUpdate")
	public String goUpdate(String userId,ModelMap modelMap){
		Object user = null;
		try {
			user = userService.findUserById(userId);
			modelMap.put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/user/user_update";
	}
	
	/**
	 * 得到角色树
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getRolesTreeByUserId")
	@RequiresPermissions("user:getRolesTreeByUserId")
	@ResponseBody
	public Object getRolesTreeByUserId(String userId){
		Object dataMap = null;
		try {
			dataMap = userService.getRolesTreeByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	/**
	 * 保存或更新用户
	 * @param user
	 * @param userId
	 * @param treeId
	 * @return
	 */
	@RequestMapping("/updateUser")
	@RequiresPermissions("user:updateUser")
	@ResponseBody
	public Object updateUser(User user,String userId,String treeId){
		Object obj = null;
		try {
			obj = userService.updateUser(user, userId, treeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping("/deleteUser")
	@RequiresPermissions("user:deleteUser")
	@ResponseBody
	public Object deleteUser(String userId){
		Object obj = userService.deleteUser(userId);
		return obj;
	}
}
