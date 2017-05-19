package com.weixin.honey.font.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.font.service.CategoryDetailService;
import com.weixin.honey.manager.service.CategoryService;
import com.weixin.honey.pojo.Category;
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
	
	private static volatile Object categoryGirlSet = null;

	/**
	 * 根据categoryId获取该种类的妹纸
	 */
	@Override
	public Object getCategoryGirlByCategoryId(String categoryId) throws Exception {
		if(!StringUtils.isBlank(categoryId) && StringUtils.isNumeric(categoryId)){
			categoryGirlSet = redisUtils.getValue(categoryGirlRedis+categoryId);
			if(categoryGirlSet == null){
				logger.info("redis中不含有该种类("+categoryId+")的妹纸");
				synchronized (CategoryDetailServiceImpl.class) {
					if(categoryGirlSet == null){
						logger.info("从数据库中查出该种类("+categoryId+")妹纸");
						Category category = (Category) categoryService.findCategoryById(categoryId);
						if(category != null){
							categoryGirlSet = category.getGirl();
							redisUtils.setValue(categoryGirlRedis+categoryId, category.getGirl());
							logger.info("该种类("+categoryId+")妹纸查出成功并放入到了redis中");
						}
					}
				}
			}else{
				logger.info("redis含有该种类("+categoryId+")妹纸，直接从redis中取出");
			}
		}
		return categoryGirlSet;
	}

}
