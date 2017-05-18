package com.weixin.honey.manager.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.manager.service.BannerService;
import com.weixin.honey.pojo.Banner;
import com.weixin.honey.util.RedisUtils;

/**
 * 轮播图实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月25日下午3:05:13
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
	
	private static final Logger logger = Logger.getLogger(BannerServiceImpl.class);
	
	@Autowired
	private BaseDao<Banner, Serializable> bannerDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Value("${bannerRedis}")
	private String bannerRedis;
	
	private volatile static List<Object> bannerList;
	
	/**
	 * 查找所有的banner
	 * 利用单例模式
	 */
	@Override
	public Object findAllBanner() throws Exception {
		bannerList = redisUtils.getList(bannerRedis, 0, -1);
		if(bannerList == null || bannerList.size() == 0){
			//redis中没有banner数据，需要将数据放入到redis中
			logger.info("从数据库取出banner信息并放入到redis中");
			synchronized (BannerServiceImpl.class) {
				if(bannerList == null || bannerList.size() == 0){
					String hql = "from Banner b where b.delflag=0 order by b.sort";
					bannerList = bannerDao.findByHql(hql);
					for(Object obj:bannerList){
						//将值放入到redis中
						Banner banner = (Banner) obj;
						redisUtils.setList(bannerRedis, banner);
					}
					logger.info("全部banner已经放入到redis中");
				}
			}
			return bannerList;
		}else{
			//直接从redis中取出
			logger.info("直接从redis取出banner信息");
			return bannerList;
		}
	}

	/**
	 * 添加或者更新banner
	 */
	@Override
	public Object updateBanner(Banner banner) throws Exception {
		String result = "";
		if(banner != null && banner.getBannerId() == 0){
			//新增
			bannerDao.save(banner);
			result = "1";
			logger.info("新增banner成功");
		}else{
			result = "2";
		}
		
		redisUtils.delete(bannerRedis);
		
		return result;
	}

	/**
	 * 删除banner
	 */
	@Override
	public Object deleteBanner(String bannerId) throws Exception {
		String result = "0";
		if(!StringUtils.isBlank(bannerId) && StringUtils.isNumeric(bannerId)){
			//进行删除
			Banner banner = bannerDao.findById(Banner.class, Integer.parseInt(bannerId));
			if(banner != null){
				banner.setDelflag(1);
				bannerDao.update(banner);
				result = "1";
			}
		}
		
		redisUtils.delete(bannerRedis);
		
		return result;
	}

}
