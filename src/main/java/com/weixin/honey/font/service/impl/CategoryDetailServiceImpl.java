package com.weixin.honey.font.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.font.service.CategoryDetailService;
import com.weixin.honey.manager.service.CategoryService;
import com.weixin.honey.pojo.Category;
import com.weixin.honey.pojo.Girl;
import com.weixin.honey.util.RedisUtils;

/**
 * 种类接口实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月19日下午4:32:19
 */
@Service
@Transactional
public class CategoryDetailServiceImpl implements CategoryDetailService {
	
	private static final Logger logger = Logger.getLogger(CategoryDetailServiceImpl.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@Value("${categoryGirlRedis}")
	private String categoryGirlRedis;
	
	@Autowired
	private RedisUtils redisUtils;
	
	private static volatile List<Object> categoryGirlList = new ArrayList<Object>();

	/**
	 * 根据categoryId获取该种类的妹纸
	 */
	@Override
	public Object getCategoryGirlByCategoryId(String categoryId,long start, long end) throws Exception {
		List<Object> categoryDetailList = new ArrayList<Object>();
		if(!StringUtils.isBlank(categoryId) && StringUtils.isNumeric(categoryId)){
			//首先判断该种类的妹纸在redis中是否存在
			categoryGirlList = redisUtils.getList(categoryGirlRedis+categoryId, 0, -1);
			if(categoryGirlList == null || categoryGirlList.size() == 0){
				//redis中不存在该种类，需要从数据库中取出
				synchronized (CategoryDetailServiceImpl.class) {
					logger.info("redis中不存在该种类("+categoryId+")的妹纸，需要从数据库中取出");
					if(categoryGirlList == null || categoryGirlList.size() == 0){
						Category category = (Category) categoryService.findCategoryById(categoryId);
						if(category != null){
							Set<Girl> girlSet = category.getGirl();
							Iterator<Girl> girlIter = girlSet.iterator();
							while(girlIter.hasNext()){
								Girl girlIt = girlIter.next();
								categoryGirlList.add(girlIt);
								redisUtils.setList(categoryGirlRedis+categoryId, girlIt);
								categoryDetailList.add(girlIt);
							}
						}
					}
				}
			}else{
				//redis中存在该种类，直接从redis中读取
				categoryDetailList = redisUtils.getList(categoryGirlRedis+categoryId, start, end);
				logger.info("redis中存在该种类妹纸，直接从redis中读取");
			}
		}
		return categoryDetailList;
	}

}
