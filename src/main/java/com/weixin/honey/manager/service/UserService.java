package com.weixin.honey.manager.service;

import com.weixin.honey.pojo.User;

/**
 * user业务层
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月17日上午9:31:18
 */
public interface UserService {

	/**
	 * 根据条件分页查找用户
	 * @param user     用户
	 * @param pageNum  传进来的页码
	 * @param length   查找length条数据
	 * @return
	 */
	public Object findPageUser(User user,String pageNum,String length) throws Exception;
	
	/**
	 * 根据用户id查找用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Object findUserById(String userId) throws Exception;
	
	/**
	 * 查找用户信息和角色
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Object getRolesTreeByUserId(String userId) throws Exception;
	
	/**
	 * 保存或更新用户
	 * @param user
	 * @param userId
	 * @param treeId
	 * @return
	 * @throws Exception
	 */
	public Object updateUser(User user,String userId,String treeId) throws Exception;
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public Object deleteUser(String userId);
}
