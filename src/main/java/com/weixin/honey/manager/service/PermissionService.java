package com.weixin.honey.manager.service;

import com.weixin.honey.pojo.Permission;

/**
 * 权限service
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月17日下午5:28:31
 */
public interface PermissionService {
	
	/**
	 * 分页查找权限
	 * @param name    权限名称
	 * @param pageNum 页码
	 * @param length  一页展示条数
	 * @return
	 */
	public Object findPagePermission(String name,String pageNum,String length);
	

	/**
	 * 查找父级权限
	 * @return
	 */
	public Object findFatherPermission() throws Exception;
	
	/**
	 * 保存或者更新权限
	 * @param type
	 * @param father_id
	 * @param permission
	 * @return
	 * @throws Exception
	 */
	public Object updatePermission(String type,String father_id,Permission permission) throws Exception;
	
	/**
	 * 根据id删除权限
	 * @param id
	 * @return
	 */
	public Object deletePermissionById(String id) throws Exception;
	
}
