package com.weixin.honey.manager.service;

import java.util.List;

import com.weixin.honey.pojo.Banner;

/**
 * 轮播图service
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月25日下午3:02:33
 */
public interface BannerService {

	/**
	 * 查找所有的banner
	 * @return
	 * @throws Exception
	 */
	public Object findAllBanner() throws Exception;
	
	/**
	 * 添加或者更新banner
	 * @param banner
	 * @return
	 * @throws Exception
	 */
	public Object updateBanner(Banner banner) throws Exception;
	
	/**
	 * 删除banner，实际上只是
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	public Object deleteBanner(String bannerId) throws Exception;
}
