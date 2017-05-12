package com.weixin.honey.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 将增删改查封装
 * @author  lujiawei
 * @version V1.0
 * @date    2017年3月24日上午10:19:27
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T,PK extends Serializable> {

	/**
	 * 保存对象
	 * @param t
	 * @return
	 */
	boolean save(final T t);
	
	/**
	 * 更新对象
	 * @param t
	 * @return
	 */
	boolean update(T t);
	
	/**
	 * 删除对象
	 * @param t
	 * @return
	 */
	boolean delete(T t);
	
	/**
	 * 删除所有对象
	 * @param t
	 * @return
	 */
	boolean deleteAll(T t);
	
	/**
	 * 根据id删除对象
	 * @param entityClass
	 * @param id
	 * @return
	 */
	boolean deleteById(Class<T> entityClass,PK id);
	
	/**
	 * 查找全部
	 * @param entityClass
	 * @return
	 */
	List<T> findAll(final Class<T> entityClass);
	
	/**
	 * 根据id查找
	 * @param entityClass
	 * @param id
	 * @return
	 */
	T findById(Class<T> entityClass,PK id);
	
	/**
	 * 但条件查找，返回第一个对象
	 * @param propertyName
	 * @param value
	 * @param tClass
	 * @return
	 */
	T findObjByProperty(String propertyName, Object value,Class<T> tClass);
	
	/**
	 * 但条件查找，返回多个对象
	 * @param propertyName
	 * @param value
	 * @param tClass
	 * @return
	 */
	List<T> findByProperty(String propertyName, Object value,Class<T> tClass);
	
	/**
	 * 多条件查找
	 * @param propertyName
	 * @param value
	 * @param tClass
	 * @return
	 */
	List<T> findIn(String propertyName, Object[] value,Class<T> tClass);

	/**
	 * 根据sql查找
	 * @param sql
	 * @return
	 */
	List findBySQL(String sql);

	/**
	 * 根据sql删除
	 * @param sql
	 * @param obj  参数
	 */
	void deleteBySql(String sql,Object[] obj);
	
	/**
	 * 根据hql查找
	 * @param hql
	 * @return
	 */
	List findByHql(String hql);
	
	/**
	 * 分页查找
	 * @param hql     hql语句
	 * @param offset  从第offset开始查
	 * @param length  查length条
	 * @return
	 */
	public List page(final String hql,final int offset,final int length);
	
	/**
	 * 分页查找，并返回相应数据
	 * @param hql
	 * @param offset
	 * @param length
	 * @return
	 */
	public Map<String, Object> pageResult(final String hql,final int offset,final int length);
}