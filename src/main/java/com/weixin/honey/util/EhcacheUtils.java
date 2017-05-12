package com.weixin.honey.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 说明：Ehcache缓存工具类
 * @author lujiawei
 * 2015年12月22日21:02:02
 *
 */
@Component
public class EhcacheUtils {

	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * 方法说明：将数据放入缓存中
	 * @param cacheName    需要使用的Cache名称
	 * @param key          放入缓存key值
	 * @param value        放入缓存value值
	 */
	public void setCache(String cacheName,Object key,Object value){
		Cache cache = cacheManager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}
	
	/**
	 * 方法说明：根据Key从缓存取数据
	 * @param cacheName    需要使用的Cache名称
	 * @param key          需要读取的key值
	 * @return
	 */
	public Object getCache(String cacheName,String key){
		Cache cache = cacheManager.getCache(cacheName);
		Element element = cache.get(key);
		if(element != null){
			return element.getObjectValue();
		}else{
			return null;
		}
	}
	
	/**
	 * 方法说明：根据key将缓存移除
	 * @param cacheName    需要使用的Cache名称
	 * @param key          需要移除的key
	 */
	public void removeCache(String cacheName,String key){
		Cache cache = cacheManager.getCache(cacheName);
		cache.remove(key);
	}
	
	/**
	 * 方法说明：移除所有缓存
	 * @param cacheName    需要使用的Cache名称
	 */
	public void removeAllCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		cache.removeAll();
	}
}
