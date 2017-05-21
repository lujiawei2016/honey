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
	 * @param categorys
	 * @return
	 * @throws Exception
	 */
	public Object update(Girl girl,String girlImgs,String categorys) throws Exception;
	
	/**
	 * 根据id查找妹纸
	 * @param girlId
	 * @return
	 * @throws Exception
	 */
	public Object findGirlById(String girlId) throws Exception;
	
	/**
	 * 从数据看中查出妹纸
	 * @param girl
	 * @return
	 * @throws Exception
	 */
	public Object findGirlFromDatabase(Girl girl) throws Exception;
	
	/**
	 * 从redis中查出普通妹纸
	 * @param start 从start开始查
	 * @param end   end条结束
	 * @return
	 * @throws Exception
	 */
	public Object findGirlFromRedis(long start, long end) throws Exception;
	
	/**
	 * 从redis总查出VIP妹纸
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public Object findVipGirlFromRedis(long start, long end) throws Exception;
	
	/**
	 * 删除图片
	 * @param girlImgId
	 * @return
	 * @throws Exception
	 */
	public Object deleteGirlImgsById(String girlImgId) throws Exception;
	
	/**
	 * 删除或者恢复妹纸
	 * @param girlId
	 * @return
	 * @throws Exception
	 */
	public Object deleteOrLifelById(String girlId) throws Exception;
	
	/**
	 * 根据妹纸id查出相应图片
	 * @param girlId
	 * @return
	 * @throws Exception
	 */
	public Object findGirlImgsByGirlId(String girlId) throws Exception;
}
