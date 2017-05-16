package com.weixin.honey.manager.service.impl;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.manager.service.GirlService;
import com.weixin.honey.pojo.Girl;
import com.weixin.honey.pojo.GirlImg;

/**
 * 妹纸service实现类
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月16日下午9:59:26
 */
@Service
@Transactional
public class GirlServiceImpl implements GirlService {
	
	@Autowired
	private BaseDao<Girl, Serializable> girlDao;
	
	@Autowired
	private BaseDao<GirlImg, Serializable> girlImgDao;

	/**
	 * 新增或者更新妹纸
	 */
	@Override
	public Object update(Girl girl, String girlImgs) throws Exception {
		String result = "";
		if(girl.getGirlId() == 0){
			//新增girl
			girlDao.save(girl);
			if(!StringUtils.isBlank(girlImgs) && girlImgs.contains(",")){
				String girlImgsArr[] = girlImgs.split(",");
				for(int i=0;i<girlImgsArr.length;i++){
					GirlImg girlImg = new GirlImg(girlImgsArr[i], girl);
					girlImgDao.save(girlImg);
				}
			}
			result = "1";
		}else{
			//编辑girl
		}
		return result;
	}

}
