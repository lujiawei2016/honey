package com.weixin.honey.font.service;

/**
 * 种类接口
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月19日下午4:30:07
 */
public interface CategoryDetailService {

	/**
	 * 根据categoryId获取该种类的妹纸
	 * @return
	 * @throws Exception
	 */
	public Object getCategoryGirlByCategoryId(String categoryId) throws Exception;
}
