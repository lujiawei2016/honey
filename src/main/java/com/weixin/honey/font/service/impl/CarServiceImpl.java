package com.weixin.honey.font.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.font.service.CarService;
import com.weixin.honey.pojo.Car;
import com.weixin.honey.pojo.Girl;
import com.weixin.honey.util.RedisUtils;

/**
 * 备战区实现类
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月21日下午1:11:10
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {
	
	private static final Logger logger = Logger.getLogger(CarServiceImpl.class);
	
	@Autowired
	private BaseDao<Car, Serializable> carDao;
	
	@Autowired
	private BaseDao<Girl, Serializable> girlDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${carRedis}")
	private String carRedis;
	
	/**
	 * 列出本站区妹纸
	 */
	@Override
	public Object listCar(int userId, long start, long end) {
		List<Object> carList = new ArrayList<>();
		carList = redisUtils.getList(carRedis+userId, 0, -1);
		if(carList == null || carList.size() == 0){
			logger.info("该用户在redis备战区中没有妹纸，需要从redis中取出");
			String hql = "from Car c where c.userId="+userId+" and c.delflag=0";
			carList = carDao.findByHql(hql);
			for(Object obj:carList){
				redisUtils.setList(carRedis+userId, obj);
			}
		}else{
			logger.info("该用户在redis备战区中有妹纸，直接从redis中取出");
			carList = redisUtils.getList(carRedis+userId, start, end);
		}
		return carList;
	}

	/**
	 * 加入备战区
	 */
	@Override
	public Object addCar(int userId, String girlId) throws Exception {
		String result = "0";
		if(!StringUtils.isBlank(girlId) && StringUtils.isNumeric(girlId)){
			Girl girl = girlDao.findById(Girl.class, Integer.parseInt(girlId));
			if(girl != null){
				Car car = new Car(userId, girl.getGirlId());
				carDao.save(car);
				
				redisUtils.setList(carRedis+userId, car);
				
				result = "1";
				logger.info("用户("+userId+")加入备战区成功");
			}
		}
		return result;
	}

	/**
	 * 删除备战区
	 */
	@Override
	public Object deleteCar(String carIds) throws Exception {
		if(!StringUtils.isBlank(carIds)){
			
		}
		return null;
	}

}
