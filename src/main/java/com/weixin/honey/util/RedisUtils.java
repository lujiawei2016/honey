package com.weixin.honey.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis工具类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月12日上午11:30:12
 */
@Component
public class RedisUtils {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 将值放入redis中
	 * @param key
	 * @param value
	 */
	public void setValue(String key,Object value){
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value);
	}

	/**
	 * 将值放入redis中
	 * @param key
	 * @param value
	 * @param timeout  过期时间
	 * @param unit     时间单位
	 */
	public void setValue(String key,Object value,long timeout, TimeUnit unit){
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value, timeout, unit);
	}
	
	/**
	 * 将值从redis中取出
	 * @param key
	 * @return
	 */
	public Object getValue(String key){
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		return opsForValue.get(key);
	}
	
	/**
	 * 删除redis值
	 * @param key
	 */
	public void removeValue(String key){
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, "", 1, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 将值放入到list中
	 * @param key
	 * @param value
	 */
	public void setList(String key,Object value){
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		opsForList.leftPush(key, value);
	}
	
	/**
	 * 将值放到list的制定位置
	 * @param key
	 * @param index
	 * @param value
	 */
	public void setList(String key,long index,Object value){
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		opsForList.set(key, index, value);
	}
	
	/**
	 * 取出制定范围内的list
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Object> getList(String key, long start, long end){
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		List<Object> range = opsForList.range(key, start, end);
		return range;
	}
	
	/**
	 * 删除指定位置的list值
	 * @param key
	 * @param i     删除个数
	 * @param value
	 */
	public void removeList(String key,long i, Object value){
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		opsForList.remove(key, i, value);
	}
	
	/**
	 * 去除指定缓存
	 * @param key
	 */
	public void delete(String keys){
		redisTemplate.delete(keys);
	}
	
}
