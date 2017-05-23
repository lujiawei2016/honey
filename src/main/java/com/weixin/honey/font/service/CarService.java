package com.weixin.honey.font.service;

/**
 * 备战区 service
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月21日下午1:10:01
 */
public interface CarService {
	
	/**
	 * 列出备战区妹纸
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 */
	public Object listCar(int userId,long start,long end);

	/**
	 * 加入备战区
	 * @param userId
	 * @param girlId
	 * @return
	 * @throws Exception
	 */
	public Object addCar(int userId,String girlId) throws Exception;
	
	/**
	 * 删除备战区
	 * @return
	 * @throws Exception
	 */
	public Object deleteCar(String girlIds,int userId) throws Exception;
	
}
