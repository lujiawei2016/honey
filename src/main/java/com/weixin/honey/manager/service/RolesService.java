package com.weixin.honey.manager.service;

/**
 * 角色接口
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月18日下午3:15:21
 */
public interface RolesService {
	
	/**
	 * 分页查找角色
	 * @param rolesName
	 * @param pageNum
	 * @param length
	 * @return
	 */
	public Object findPageRoles(String rolesName, String pageNum, String length);
	
	/**
	 * 查找父角色
	 * @return
	 * @throws Exception
	 */
	public Object findFatherRoles() throws Exception;

	/**
	 * 根据角色id获取相应权限树
	 * @param rolesId
	 * @return
	 * @throws Exception
	 */
	public Object getPermissionTreeByRolesId(String rolesId) throws Exception;
	
	/**
	 * 保存或者更新角色
	 * @param type
	 * @param name
	 * @param father_id
	 * @param rolesId
	 * @param treeId
	 * @return
	 */
	public Object updateRoles(String type,String name,String father_id,String rolesId,String treeId) throws Exception;
	
	/**
	 * 根据id查找角色
	 * @param rolesId
	 * @return
	 * @throws Exception
	 */
	public Object findRolesById(String rolesId) throws Exception;
	
	/**
	 * 根据id删除角色
	 * @param rolesId
	 * @return
	 * @throws Exception
	 */
	public Object deleteRolesById(String rolesId) throws Exception;
}
