package com.weixin.honey.manager.service;

import com.weixin.honey.pojo.Category;

/**
 * 图标service接口
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月14日上午10:17:51
 */
public interface CategoryService {
	
	/**
	 * 查找所有的图标
	 * @return
	 * @throws Exception
	 */
	public Object findAllCategory() throws Exception;

	/**
	 * 更新图标
	 * @param category
	 * @return
	 * @throws Exception
	 */
	public Object updateCategory(Category category) throws Exception;
	
	/**
	 * 删除种类图标
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	public Object deleteCategory(String categoryId) throws Exception;
}
