package com.weixin.honey.manager.service.impl;

import java.io.Serializable;
import java.util.HashSet;
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
	private RedisUtils redisUtils;
	
	@Value("${girlListRedis}")
	private String girlListRedis;   // list girl主键

	/**
	 * 新增或者更新妹纸
	 */
	@Override
	public Object update(Girl girl, String girlImgs) throws Exception {
		String result = "";
		Set<GirlImg> girlImgSet = new HashSet<>();
		if(girl.getGirlId() == 0){
			//新增girl
			girlDao.save(girl);
			if(!StringUtils.isBlank(girlImgs) && girlImgs.contains(",")){
				String girlImgsArr[] = girlImgs.split(",");
				for(int i=0;i<girlImgsArr.length;i++){
					GirlImg girlImg = new GirlImg(girlImgsArr[i], girl);
					girlImgDao.save(girlImg);
					girlImgSet.add(girlImg);
				}
			}
			
			girl.setGirlImg(girlImgSet);
			girlDao.update(girl);
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
			if(!StringUtils.isBlank(girlImgs) && girlImgs.contains(",")){
				String girlImgsArr[] = girlImgs.split(",");
				for(int i=0;i<girlImgsArr.length;i++){
					GirlImg girlImg = new GirlImg(girlImgsArr[i], girl);
					girlImgDao.save(girlImg);
					girlImgSet.add(girlImg);
				}
			}
			girlDao.update(oldGirl);
			logger.info("编辑妹纸成功");
			result = "2";
		}
		
		//移除redis中的列表，待查询时重新排序
		redisUtils.delete(girlListRedis);
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
		}else{
			logger.info("girlId为空");
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
		
		bufferHql.append("order by g.sort desc ");
		
		List<Girl> girlList = girlDao.findByHql(bufferHql.toString());
		
		return girlList;
	}

	/**
	 * 从redis查出妹纸
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object findGirlFromRedis(long start, long end) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
				girlImgDao.delete(girlImg);
				result = "1";
				logger.info("删除图片成功");
			}
		}
		return result;
	}

}
