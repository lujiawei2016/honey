package com.weixin.honey.manager.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.manager.service.GirlService;
import com.weixin.honey.pojo.Category;
import com.weixin.honey.pojo.Girl;
import com.weixin.honey.pojo.GirlImg;
import com.weixin.honey.util.RedisUtils;

/**
 * 妹纸service实现类
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月16日下午9:59:26
 */
@Service
@Transactional
public class GirlServiceImpl implements GirlService {
	
	private static final Logger logger = Logger.getLogger(GirlServiceImpl.class);
	
	@Autowired
	private BaseDao<Girl, Serializable> girlDao;
	
	@Autowired
	private BaseDao<GirlImg, Serializable> girlImgDao;
	
	@Autowired
	private BaseDao<Category, Serializable> categoryDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${girlListRedis}")
	private String girlListRedis;   // list girl主键
	
	@Value("${girlVipListRedis}")
	private String girlVipListRedis;
	
	@Value("${girlSingleRedis}")
	private String girlSingleRedis;
	
	@Value("${girlImgsRedis}")
	private String girlImgsRedis;
	
	@Value("${categoryGirlRedis}")
	private String categoryGirlRedis;
	
	@Value("${girlActiveRedis}")
	private String girlActiveRedis;
	
	private static volatile List<Object> girlRedisList = new ArrayList<Object>();
	
	private static volatile List<Object> girlVipRedisList = new ArrayList<Object>();
	
	private static volatile List<Object> girlActiveRedisList = new ArrayList<Object>();

	/**
	 * 新增或者更新妹纸
	 */
	@Override
	public Object update(Girl girl, String girlImgs,String categorys) throws Exception {
		String result = "";
		Set<GirlImg> girlImgSet = new HashSet<GirlImg>();
		Set<Category> categorySet = new HashSet<Category>();
		
		if(girl.getGirlId() == 0){
			//新增girl
			
			//种类
			if(!StringUtils.isBlank(categorys)){
				if(categorys.contains(",")){
					//该妹纸多个种类
					String[] categoryArr = categorys.split(",");
					for(int i=0;i<categoryArr.length;i++){
						Category category = categoryDao.findById(Category.class, Integer.parseInt(categoryArr[i]));
						categorySet.add(category);
						
						redisUtils.delete(categoryGirlRedis+categoryArr[i]);
						logger.info("新增妹纸的时候将原来的种类移除");
					}
				}else{
					//该妹纸一个种类
					Category category = categoryDao.findById(Category.class, Integer.parseInt(categorys));
					categorySet.add(category);
					
					redisUtils.delete(categoryGirlRedis+categorys);
					logger.info("新增妹纸的时候将原来的种类移除");
				}
				girl.setCategory(categorySet);
			}
			
			//保存妹纸的图片
			if(!StringUtils.isBlank(girlImgs) && girlImgs.contains(",")){
				String girlImgsArr[] = girlImgs.split(",");
				for(int i=0;i<girlImgsArr.length;i++){
					GirlImg girlImg = new GirlImg(girlImgsArr[i], girl);
					girlImgDao.save(girlImg);
					girlImgSet.add(girlImg);
				}
			}
			girl.setGirlImg(girlImgSet);
			
			//保存
			girlDao.save(girl);
			logger.info("新增妹纸成功");
			result = "1";
		}else{
			//编辑girl
			Girl oldGirl = girlDao.findById(Girl.class, girl.getGirlId());
			oldGirl.setMainImg(girl.getMainImg());
			oldGirl.setGirlName(girl.getGirlName());
			oldGirl.setAge(girl.getAge());
			oldGirl.setHight(girl.getHight());
			oldGirl.setWeight(girl.getWeight());
			oldGirl.setQq(girl.getQq());
			oldGirl.setWeixin(girl.getWeixin());
			oldGirl.setPhone(girl.getPhone());
			oldGirl.setPrice(girl.getPrice());
			oldGirl.setAddress(girl.getAddress());
			oldGirl.setTitle(girl.getTitle());
			oldGirl.setDescription(girl.getDescription());
			oldGirl.setSort(girl.getSort());
			oldGirl.setIsVip(girl.getIsVip());
			oldGirl.setIsActivity(girl.getIsActivity());
			
			if(!StringUtils.isBlank(girlImgs) && girlImgs.contains(",")){
				String girlImgsArr[] = girlImgs.split(",");
				for(int i=0;i<girlImgsArr.length;i++){
					GirlImg girlImg = new GirlImg(girlImgsArr[i], girl);
					girlImgDao.save(girlImg);
					girlImgSet.add(girlImg);
				}
			}
			
			//种类
			if(!StringUtils.isBlank(categorys)){
				if(categorys.contains(",")){
					//该妹纸多个种类
					String[] categoryArr = categorys.split(",");
					for(int i=0;i<categoryArr.length;i++){
						Category category = categoryDao.findById(Category.class, Integer.parseInt(categoryArr[i]));
						categorySet.add(category);
						
						redisUtils.delete(categoryGirlRedis+categoryArr[i]);
						logger.info("编辑妹纸的时将所属的种类移除");
					}
				}else{
					//该妹纸一个种类
					Category category = categoryDao.findById(Category.class, Integer.parseInt(categorys));
					categorySet.add(category);
					
					redisUtils.delete(categoryGirlRedis+categorys);
					logger.info("编辑妹纸的时将所属的种类移除");
				}
				
				//需要将原来的所属种类全部移除
				logger.info("编辑妹纸时需将原来的种类全部移除");
				Set<Category> oldCategorySet = oldGirl.getCategory();
				Iterator<Category> categoryIter = oldCategorySet.iterator();
				while(categoryIter.hasNext()){
					int categoryId = categoryIter.next().getCategoryId();
					redisUtils.delete(categoryGirlRedis+categoryId);
				}
				
				oldGirl.setCategory(categorySet);
			}
			
			girlDao.update(oldGirl);
			redisUtils.delete(girlSingleRedis+oldGirl.getGirlId());
			
			logger.info("编辑妹纸成功");
			result = "2";
		}
		
		//移除redis中的列表，待查询时重新排序
		redisUtils.delete(girlListRedis);
		redisUtils.delete(girlVipListRedis);
		redisUtils.delete("girlActiveRedis");
		
		logger.info("移除redis中的列表，待查询时重新排序");
		
		return result;
	}

	/**
	 * 根据id查找妹纸
	 */
	@Override
	public Object findGirlById(String girlId) throws Exception {
		Girl girl = null;
		if(!StringUtils.isBlank(girlId) && StringUtils.isNumeric(girlId)){
			girl = girlDao.findById(Girl.class, Integer.parseInt(girlId));
			logger.info("根据girlId("+girlId+")查出妹纸");
		}
		return girl;
	}

	/**
	 * 从数据库查出妹纸
	 * @param girl
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object findGirlFromDatabase(Girl girl) throws Exception {
		StringBuffer bufferHql = new StringBuffer();
		bufferHql.append("from Girl g where 1=1 ");
		
		if(!StringUtils.isBlank(girl.getGirlName())){
			bufferHql.append("and g.girlName like '%"+girl.getGirlName()+"%' ");
		}
		
		if(!StringUtils.isBlank(girl.getPhone())){
			bufferHql.append("and g.phone like '%"+girl.getPhone()+"%' ");
		}
		
		bufferHql.append("order by g.sort desc,g.delflag asc ");
		
		List<Girl> girlList = girlDao.findByHql(bufferHql.toString());
		
		return girlList;
	}

	/**
	 * 从redis查出普通妹纸
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object findGirlFromRedis(long start, long end) throws Exception {
		girlRedisList = redisUtils.getList(girlListRedis, 0, -1);
		List<Object> girlList = new ArrayList<Object>();
		if(girlRedisList == null || girlRedisList.size() == 0){
			//redis中没有妹纸，需要从数据看中查出并放入到redis中
			synchronized (GirlServiceImpl.class) {
				logger.info("正在查询妹纸放入到数据库中");
				if(girlRedisList == null || girlRedisList.size() == 0){
					String hql = "from Girl g where g.delflag=0 and g.isVip=0 and g.isActivity=0 order by g.sort asc";
					girlRedisList = girlDao.findByHql(hql);
					for(Object obj:girlRedisList){
						redisUtils.setList(girlListRedis, obj);
					}
					logger.info("全部普通妹纸已经放入redis中");
				}
			}
		}else{
			//redis中有妹纸，直接从redis中取出
			logger.info("redis中有普通妹纸，直接从redis中取出");
			girlList = redisUtils.getList(girlListRedis, start, end);
		}
		return girlList;
	}
	
	/**
	 * 从redis总查出VIP妹纸
	 */
	@Override
	public Object findVipGirlFromRedis(long start, long end) throws Exception {
		girlVipRedisList = redisUtils.getList(girlVipListRedis, 0, -1);
		List<Object> girlList = new ArrayList<Object>();
		if(girlVipRedisList == null || girlVipRedisList.size() == 0){
			synchronized (GirlServiceImpl.class) {
				if(girlVipRedisList == null || girlVipRedisList.size() == 0){
					//redis中没有VIP妹纸，需要从数据库中查出
					logger.info("正在数据库中查出VIP妹纸放入到数据库中");
					String hql = "from Girl g where g.delflag=0 and g.isVip=1 order by g.sort asc";
					girlVipRedisList = girlDao.findByHql(hql);
					for(Object obj:girlVipRedisList){
						redisUtils.setList(girlVipListRedis, obj);
					}
					logger.info("全部VIP妹纸已经放入到redis中");
				}
			}
		}else{
			logger.info("redis中有VIP妹纸，直接从redis中取出");
			girlList = redisUtils.getList(girlVipListRedis, start, end);
		}
		return girlList;
	}
	
	/**
	 * 从redis中查找活动妹纸
	 */
	@Override
	public Object findActiveGirlFromRedis(long start, long end) throws Exception {
		girlActiveRedisList = redisUtils.getList(girlActiveRedis, 0, -1);
		List<Object> girlList = new ArrayList<Object>();
		if(girlActiveRedisList == null || girlActiveRedisList.size() == 0){
			synchronized (GirlServiceImpl.class) {
				if(girlActiveRedisList == null || girlActiveRedisList.size() == 0){
					//redis中没活动妹纸，需要从数据库中查出
					logger.info("正在数据库中查出活动妹纸放入到数据库中");
					String hql = "from Girl g where g.delflag=0 and g.isActivity=1 order by g.sort asc";
					girlActiveRedisList = girlDao.findByHql(hql);
					for(Object obj:girlActiveRedisList){
						redisUtils.setList(girlActiveRedis, obj);
					}
					logger.info("全部活动妹纸已经放入到redis中");
				}
			}
		}else{
			logger.info("redis中有活动妹纸，直接从redis中取出");
			girlList = redisUtils.getList(girlActiveRedis, start, end);
		}
		return girlList;
	}

	/**
	 * 删除图片
	 */
	@Override
	public Object deleteGirlImgsById(String girlImgId) throws Exception {
		String result = "";
		if(!StringUtils.isBlank(girlImgId) && StringUtils.isNumeric(girlImgId)){
			GirlImg girlImg = girlImgDao.findById(GirlImg.class, Integer.parseInt(girlImgId));
			if(girlImg != null){
				
				int girlId = girlImg.getGirl().getGirlId();
				
				girlImgDao.delete(girlImg);
				redisUtils.delete(girlImgsRedis+girlId);
				
				result = "1";
				logger.info("删除妹纸图片成功");
			}
		}
		return result;
	}

	/**
	 * 删除或者恢复妹纸
	 */
	@Override
	public Object deleteOrLifelById(String girlId) throws Exception {
		String result = "";
		if(!StringUtils.isBlank(girlId) && StringUtils.isNumeric(girlId)){
			Girl girl = girlDao.findById(Girl.class, Integer.parseInt(girlId));
			if(girl != null){
				if(girl.getDelflag() == 0){
					girl.setDelflag(1);
					girlDao.update(girl);
					result = "1";
					
					redisUtils.delete(girlSingleRedis+girlId);
					
					logger.info("妹纸删除成功并将redis中移除");
				}else if(girl.getDelflag() == 1){
					girl.setDelflag(0);
					girlDao.update(girl);
					result = "2";
					logger.info("妹纸复活成功");
				}
			}
		}
		
		//移除redis中的列表，待查询时重新排序
		redisUtils.delete(girlListRedis);
		logger.info("移除redis中的列表，待查询时重新排序");
		
		return result;
	}

	/**
	 * 根据妹纸id查出相应图片
	 */
	@Override
	public Object findGirlImgsByGirlId(String girlId) throws Exception {
		List<GirlImg> girlImgList = new ArrayList<GirlImg>();
		if(!StringUtils.isBlank(girlId) && StringUtils.isNumeric(girlId)){
			String hql = "from GirlImg g where g.girl.girlId="+Integer.parseInt(girlId)+"";
			girlImgList = girlImgDao.findByHql(hql);
		}
		return girlImgList;
	}

}
