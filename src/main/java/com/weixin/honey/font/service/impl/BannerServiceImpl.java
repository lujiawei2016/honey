package com.weixin.honey.font.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.font.service.BannerService;
import com.weixin.honey.pojo.Banner;

/**
 * 轮播图实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月25日下午3:05:13
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private BaseDao<Banner, Serializable> bannerDao;
	
	/**
	 * 查找所有的banner
	 */
	@Override
	public List<Banner> findAllBanner() throws Exception {
		String hql = "from Banner b where b.delflag=0 order by b.sort";
		List<Banner> bannerList = bannerDao.findByHql(hql);
		return bannerList;
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
		}else{
			result = "2";
		}
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
			banner.setDelflag(1);
			bannerDao.update(banner);
			result = "1";
		}
		return result;
	}

}
