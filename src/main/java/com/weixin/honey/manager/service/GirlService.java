package com.weixin.honey.manager.service;

import com.weixin.honey.pojo.Girl;

/**
 * 妹纸service
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月16日下午9:57:48
 */
public interface GirlService {

	/**
	 * 新增或者更新妹纸
	 * @param girl
	 * @param girlImgs
	 * @return
	 * @throws Exception
	 */
	public Object update(Girl girl,String girlImgs) throws Exception;
}
