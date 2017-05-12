package com.weixin.honey.manager.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.manager.service.UserService;
import com.weixin.honey.pojo.Roles;
import com.weixin.honey.pojo.User;
import com.weixin.honey.util.AESOperator;

/**
 * userservice实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月17日上午9:36:59
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private BaseDao<User, Serializable> userDao;
	
	@Autowired
	private BaseDao<Roles, Serializable> rolesDao;
	
	@Value("${initial.password}")
	private String initPwd;      //初始密码
	
	@Value("${salt}")
	private String salt;         //盐
	
	@Value("${pageLength}")
	private int pageLength;  //默认一页展示pageLength条数据

	/**
	 * 分页查找用户
	 */
	@Override
	public Object findPageUser(User user,String pageNum,String length) throws Exception {
		
		int pNum = 1;             //默认从第一页开始查找数据
		int lenNum = pageLength;  //默认查找pageLength条数据
		String username = "";
		
		if(pageNum != null){pNum = Integer.parseInt(pageNum);}
		if(length != null){lenNum = Integer.parseInt(length);}
		
		pNum = (pNum-1)*lenNum;
		username = user.getUsername()==null ? username : user.getUsername();
		
		String hql = "from User u where u.username like '%"+username+"%'";
		Map<String, Object> map = userDao.pageResult(hql, pNum, lenNum);
		
		return map;
	}

	/**
	 * 根据用户id查找用户
	 */
	@Override
	public Object findUserById(String userId) throws Exception {
		User user = null;
		if(!StringUtils.isBlank(userId) && StringUtils.isNumeric(userId)){
			user = userDao.findById(User.class, Integer.parseInt(userId));
		}
		return user;
	}

	/**
	 * 查找用户信息和角色
	 */
	@Override
	public Object getRolesTreeByUserId(String userId) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> treeList = new ArrayList<Map<String,Object>>();
		String tueeName = "";
		String treeId = "";
		
		if(StringUtils.isBlank(userId)){
			//新增用户
			String fatherRoleHql = "from Roles r where r.father.id is null";
			String childrenRoleHql = "from Roles r where r.father.id is not null";
			
			List<Roles> fatherRolesList = rolesDao.findByHql(fatherRoleHql);
			List<Roles> childrenRolesList = rolesDao.findByHql(childrenRoleHql);
			
			for(Roles fatherRoles:fatherRolesList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", fatherRoles.getId());
				map.put("pId", 0);
				map.put("name", fatherRoles.getRolesName());
				map.put("open", false);
				treeList.add(map);
			}
			
			for(Roles childrenRoles:childrenRolesList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", childrenRoles.getId());
				map.put("pId", childrenRoles.getFather().getId());
				map.put("name", childrenRoles.getRolesName());
				treeList.add(map);
			}
			
		}else{
			//编辑用户
			String hasRolesHql = "select s from User u,Roles s where u.id="+Integer.parseInt(userId)+" and s in elements(u.roles)";
			String noRoleshql = "from Roles s where s.father.id is not null";
			String allFatherRolesHql = "from Roles s where s.father.id is null";
			
			List<Roles> hasRolesList = rolesDao.findByHql(hasRolesHql);          //用户已经拥有的角色
			List<Roles> noRolesList = rolesDao.findByHql(noRoleshql);            //用户未拥有角色
			List<Roles> noFatherList = rolesDao.findByHql(allFatherRolesHql);    //全部父角色，主要用于存放用户未拥有的父角色
			List<Roles> hasFatherRolesList = new ArrayList<Roles>();             //用户拥有的父角色
			
			noRolesList.removeAll(hasRolesList);
			
			Set<Integer> hasRolesFatherSet = new HashSet<Integer>();
			
			//拥有的角色
			for(Roles roles:hasRolesList){
				hasRolesFatherSet.add(roles.getFather().getId());
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("id", roles.getId());
				map.put("pId", roles.getFather().getId());
				map.put("name", roles.getRolesName());
				map.put("checked", true);
				
				tueeName = tueeName + roles.getRolesName() + ",";
				treeId = treeId + roles.getId() + ",";
				
				treeList.add(map);
			}
			
			//未拥有的角色
			for(Roles roles:noRolesList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", roles.getId());
				map.put("pId", roles.getFather().getId());
				map.put("name", roles.getRolesName());
				treeList.add(map);
			}
			
			Iterator<Integer> hasRoleIter = hasRolesFatherSet.iterator();
			
			//拥有权限的父角色
			while(hasRoleIter.hasNext()){
				Roles roles = rolesDao.findById(Roles.class, hasRoleIter.next());
				hasFatherRolesList.add(roles);
			}
			
			//拥有的父角色
			for(Roles roles:hasFatherRolesList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", roles.getId());
				map.put("pId", 0);
				map.put("name", roles.getRolesName());
				map.put("open", false);
				map.put("checked", true);
				treeList.add(map);
			}
			
			noFatherList.removeAll(hasFatherRolesList);
			
			//未拥有的父角色
			for(Roles roles:noFatherList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", roles.getId());
				map.put("pId", 0);
				map.put("name", roles.getRolesName());
				map.put("open", false);
				treeList.add(map);
			}
			
		}
		
		dataMap.put("treeList", treeList);
		dataMap.put("tueeName", tueeName);
		dataMap.put("treeId", treeId);
		
		return dataMap;
	
	}

	/**
	 * 保存或更新用户
	 */
	@Override
	public Object updateUser(User user, String userId, String treeId) throws Exception {
		String result = "";
		if(StringUtils.isBlank(userId)){
			//新增用户
			User isExitUser = userDao.findObjByProperty("username", user.getUsername(), User.class);
			if(isExitUser == null){
				//改用户名不存在
				user.setPassword(AESOperator.getInstance().encrypt(initPwd+salt));
				user = getUpdateUser(user, treeId);
				userDao.save(user);
				result = "1";
			}else{
				//用户名已存在
				result = "2";
			}
		}else{
			//编辑用户
			String isExitUserHql = "from User u where u.username='"+user.getUsername()+"' and u.id <> "+Integer.parseInt(userId)+"";
			List<User> isExitUserList = userDao.findByHql(isExitUserHql);
			if(isExitUserList == null || isExitUserList.size() == 0){
				//改用户名不存在
				User exitUser = userDao.findById(User.class, Integer.parseInt(userId));
				exitUser.setUsername(user.getUsername());
				exitUser = getUpdateUser(exitUser, treeId);
				userDao.update(exitUser);
				result = "3";
			}else{
				//用户名已存在
				result = "2";
			}
		}
		return result;
	}
	
	/**
	 * 获得需要保存或者更新的用户
	 * @param user
	 * @param treeId
	 * @return
	 */
	public User getUpdateUser(User user,String treeId){
		if(!StringUtils.isBlank(treeId)){
			//已经分配角色
			Set<Roles> rolesSet = new HashSet<Roles>();
			String[] treeIds = treeId.split(",");
			for(int i=0;i<treeIds.length;i++){
				Roles roles = rolesDao.findById(Roles.class, Integer.parseInt(treeIds[i]));
				rolesSet.add(roles);
			}
			user.setRoles(rolesSet);
		}else{
			user.setRoles(null);
		}
		return user;
	}

	/**
	 * 删除用户
	 */
	@Override
	public Object deleteUser(String userId) {
		if(!StringUtils.isBlank(userId) && StringUtils.isNumeric(userId)){
			User user = userDao.findById(User.class, Integer.parseInt(userId));
			if(user != null){
				userDao.delete(user);
				return "1";
			}else{
				return "2";
			}
		}
		return null;
	}

}
