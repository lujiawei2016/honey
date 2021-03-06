package com.weixin.honey.font.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.font.service.DetailService;
import com.weixin.honey.manager.service.GirlService;
import com.weixin.honey.pojo.Girl;
import com.weixin.honey.util.RedisUtils;

/**
 * 详情页service实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月19日上午11:10:00
 */
@Service
@Transactional
public class DetailServiceImpl implements DetailService {
	
	private static final Logger logger = Logger.getLogger(DetailServiceImpl.class);
	
	@Value("${girlSingleRedis}")
	private String girlSingleRedis;
	
	@Value("${girlImgsRedis}")
	private String girlImgsRedis;
	
	@Value("${praiseRedis}")
	private String praiseRedis;      //点赞键
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private GirlService girlService;
	
	@Autowired
	private BaseDao<Girl, Serializable> girlDao;
	
	private static volatile List<Object> objList = new ArrayList<Object>();
	
	/**
	 * 根据id得到girl
	 */
	@Override
	public Object getGirl(String girlId) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Girl girl = null;
		if(!StringUtils.isBlank(girlId) && StringUtils.isNumeric(girlId)){
			Object redisGirl = redisUtils.getValue(girlSingleRedis+girlId);
			if(redisGirl == null){
				logger.info("妹纸id为"+girlId+"在redis中为空，正在放入redis中");
				girl = (Girl) girlService.findGirlById(girlId);
				if(girl != null){
					if(girl.getDelflag() == 0){
						redisUtils.setValue(girlSingleRedis+girlId, girl);
						logger.info("已经将妹纸放入到了redis中");
					}else{
						logger.info("妹纸("+girlId+")已经删除，不能放入到redis中");
						girl = null;
					}
				}
			}else{
				girl = (Girl) redisGirl;
				logger.info("妹纸id为"+girlId+"在redis中不为空，直接从redis中取出");
			}
		}
		
		//将妹纸的相关图片查出
		List<Object> girlImgList = new ArrayList<Object>();
		if(girl != null){
			objList = redisUtils.getList(girlImgsRedis+girlId, 0, -1);
			if(objList == null || objList.size() == 0){
				synchronized (DetailServiceImpl.class) {
					if(objList == null || objList.size() == 0){
						logger.info("redis中没有妹纸的相关图片，从数据看中查出相关图片放入到redis中");
						girlImgList = (List<Object>) girlService.findGirlImgsByGirlId(girlId);
						objList = girlImgList;
						for(Object girlImg:girlImgList){
							redisUtils.setList(girlImgsRedis+girlId, girlImg);
						}
					}
				}
			}else{
				girlImgList = redisUtils.getList(girlImgsRedis+girlId, 0, -1);
				logger.info("redis中有妹纸的相关图片，直接从redis中取出");
			}
			
			dataMap.put("girl", girl);
			dataMap.put("girlImgList", girlImgList);
		}
		
		return dataMap;
	}

	/**
	 * 点赞
	 */
	@Override
	public Object thumbUp(String girlid,int userId) throws Exception {
		String result = "0";
		if(!StringUtils.isBlank(girlid) && StringUtils.isNumeric(girlid)){
			//判断用户在24小时内对该妹纸是否点赞
			String isPraise = (String) redisUtils.getValue(praiseRedis+userId+girlid);
			if(isPraise == null || isPraise == ""){
				//用户在24小时内没有点赞，可以点赞
				Girl girl = girlDao.findById(Girl.class, Integer.parseInt(girlid));
				if(girl != null){
					Girl redisGirl = (Girl) redisUtils.getValue(girlSingleRedis+girlid);
					girl.setPraise(redisGirl.getPraise()+1);
					
					girlDao.update(girl);
					redisUtils.setValue(girlSingleRedis+girlid, girl);
					
					//设置24小时只能对一个妹纸点赞一次
					redisUtils.setValue(praiseRedis+userId+girlid, "1", 24, TimeUnit.HOURS);
					
					logger.info("用户("+userId+")点赞成功");
					
					result = "1";
				}
			}else{
				//用户在24小时已经点赞了，不可以再点赞
				result = "2";
			}
		}
		return result;
	}

}
