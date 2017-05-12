package com.weixin.honey.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.weixin.honey.dao.BaseDao;

/**
 * 基础dao实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年3月24日下午2:34:53
 * @param <T>
 * @param <PK>
 */
@Repository
public class BaseDaoImpl <T, PK extends Serializable> implements BaseDao<T, PK> {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * 保存
	 */
	@Override
	public boolean save(T t) {
		hibernateTemplate.save(t);
		return true;
	}

	/**
	 * 更新
	 */
	@Override
	public boolean update(T t) {
		hibernateTemplate.update(t);
		return true;
	}
	
	/**
	 * 删除
	 */
	@Override
	public boolean delete(T t){
		hibernateTemplate.delete(t);
		return true;
	}

	/**
	 * 删除全部
	 */
	@Override
	public boolean deleteAll(T t) {
		hibernateTemplate.deleteAll(this.findAll((Class<T>) t.getClass()));
		return false;
	}

	/**
	 * 根据id删除
	 */
	@Override
	public boolean deleteById(Class<T> entityClass, PK id) {
		hibernateTemplate.delete(this.findById(entityClass, id));
		return false;
	}

	/**
	 * 查找全部
	 */
	@Override
	public List<T> findAll(Class<T> entityClass) {
		return hibernateTemplate.findByCriteria(DetachedCriteria.forClass(entityClass));
	}

	/**
	 * 通过id查找
	 */
	@Override
	public T findById(Class<T> entityClass, PK id) {
		
		return hibernateTemplate.get(entityClass, id);
	}
	
	/**
	 * 根据条件查对象
	 */
	@Override
	public T findObjByProperty(String propertyName, Object value,
			Class<T> tClass) {
		T obj = null;
		String queryString = "from " + tClass.getSimpleName()
				+ " as model where model." + propertyName + "= ? ";
		List<T> tList = hibernateTemplate.find(queryString, value);
		if(tList.size() > 0){
			for(T t : tList){
				obj = t;
			}
		}
		return obj;
	}

	/**
	 * 单条件查找
	 */
	@Override
	public List<T> findByProperty(String propertyName, Object value,
			Class<T> tClass) {
		String queryString = "from " + tClass.getSimpleName()
				+ " as model where model." + propertyName + "= ? ";
		return hibernateTemplate.find(queryString, value);
	}

	/**
	 * 多条件查找
	 */
	@Override
	public List<T> findIn(String propertyName, Object[] value, Class<T> tClass) {
		DetachedCriteria date = DetachedCriteria.forClass(tClass);
		date.add(Restrictions.in(propertyName, value));
		return hibernateTemplate.findByCriteria(date);
	}
	
	/**
	 * 根据hql查找
	 */
	@Override
	public List findByHql(String hql) {
		return hibernateTemplate.find(hql);
	}

	/**
	 * 根据sql语句查找
	 */
	@Override
	public List findBySQL(final String sql) {
		List list = hibernateTemplate.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql);
				List list = query.list();
				return list;
			}
		});
		return list;
	}
	
	/**
	 * 根据sql删除
	 */
	@Override
	public void deleteBySql(final String sql,final Object[] obj) {
		hibernateTemplate.executeFind(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				for(int i=0;i<obj.length;i++){
					query.setParameter(i, obj[i]);
				}
				query.executeUpdate();
				return null;
			}
		});
	}

	/**
	 * 分页查询
	 * hql     hql语句
	 * offset  从第offset条语句开始查询
	 * length  查length条数据
	 */
	@Override
	public List page(final String hql,final int offset,final int length) {
		List lists = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException{
				Query query = session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List list = query.list();
				return list;
			}
		});
		return lists;
	}

	/**
	 * 分页查找，并返回相应数据
	 * hql     hql语句
	 * offset  从第offset条语句开始查询
	 * length  查length条数据
	 * @return
	 */
	@Override
	public Map<String, Object> pageResult(final String hql,final int offset,final int length) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		int countNum = 0;  // 一共countNum条
		int countPage = 0; // 一共countPage页

		countNum = findByHql(hql).size();
		
		List lists = page(hql, offset, length);
		if(lists != null && lists.size() != 0){
			countPage = countNum%length==0 ? countNum/length : countNum/length+1;
		}
		
		map.put("lists", lists);
		map.put("countNum", countNum);
		map.put("countPage", countPage);
		
		return map;
	}

}
