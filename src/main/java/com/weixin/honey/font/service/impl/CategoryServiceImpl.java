package com.weixin.honey.font.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.font.service.CategoryService;
import com.weixin.honey.pojo.Category;
import com.weixin.honey.util.RedisUtils;

/**
 * 图标service实现类
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月14日上午10:20:55
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private BaseDao<Category, Serializable> categoryDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${categoryRedis}")
	private String categoryRedis;
	
	private volatile static List<Object> categoryList;
	
	/**
	 * 查找所有的图标
	 */
	@Override
	public Object findAllCategory() throws Exception {
		categoryList = redisUtils.getList(categoryRedis, 0, -1);
		if(categoryList == null || categoryList.size() == 0){
			synchronized (CategoryServiceImpl.class) {
				if(categoryList == null || categoryList.size() == 0){
					logger.info("redis中没有图标数据，从redis取出");
					String hql = "from Category c where c.delflag=0 order by c.sort";
					categoryList = categoryDao.findByHql(hql);
					for(Object obj:categoryList){
						redisUtils.setList(categoryRedis, obj);
					}
				}
			}
		}
		return categoryList;
	}
	
	/**
	 * 新增图标
	 */
	@Override
	public Object updateCategory(Category category) throws Exception {
		categoryDao.save(category);
		redisUtils.delete(categoryRedis);
		return "1";
	}

	/**
	 * 删除种类图标
	 */
	@Override
	public Object deleteCategory(String categoryId) throws Exception {
		String result = "";
		if(!StringUtils.isBlank(categoryId) && StringUtils.isNumeric(categoryId)){
			Category category = categoryDao.findById(Category.class, Integer.parseInt(categoryId));
			if(category != null){
				category.setDelflag(1);
				categoryDao.update(category);
				logger.info("删除种类图标成功");
				result = "1";
			}
		}
		return result;
	}

}
