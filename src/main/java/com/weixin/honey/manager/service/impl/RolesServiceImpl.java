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
import com.weixin.honey.manager.service.RolesService;
import com.weixin.honey.pojo.Permission;
import com.weixin.honey.pojo.Roles;

/**
 * 角色实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月18日下午3:18:04
 */
@Service
@Transactional
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private BaseDao<Roles, Serializable> rolesDao;
	
	@Autowired
	private BaseDao<Permission, Serializable> permissionDao;
	
	@Value("${pageLength}")
	private int pageLength;  //默认一页展示pageLength条数据
	
	/**
	 * 分页查找角色
	 */
	@Override
	public Object findPageRoles(String rolesName, String pageNum, String length) {
		int pNum = 1;             //默认从第一页开始查找数据
		int lenNum = pageLength;  //默认查找pageLength条数据
		
		if(pageNum != null){pNum = Integer.parseInt(pageNum);}
		if(length != null){lenNum = Integer.parseInt(length);}
		
		pNum = (pNum-1)*lenNum;
		rolesName = rolesName==null ? "" : rolesName;
		
		String hql = "from Roles r where r.rolesName like '%"+rolesName+"%'";
		Map<String, Object> map = permissionDao.pageResult(hql, pNum, lenNum);
		
		return map;
	}
	
	/**
	 * 查找父角色
	 */
	@Override
	public Object findFatherRoles() throws Exception {
		String hql = "from Roles r where r.father.id is null";
		List<Roles> rolesList = rolesDao.findByHql(hql);
		return rolesList;
	}

	/**
	 * 根据角色id获取权限树
	 */
	@Override
	public Object getPermissionTreeByRolesId(String rolesId) throws Exception {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String treeName = "";
		String treeId = "";
		
		List<Map<String, Object>> treeList = new ArrayList<Map<String,Object>>();
		if(!StringUtils.isBlank(rolesId) && StringUtils.isNumeric(rolesId)){
			if("0".equals(rolesId)){
				//新增角色，需要获取所有权限，并不打钩
				String findPermissionHql = "from Permission p";
				List<Permission> permissionList = permissionDao.findByHql(findPermissionHql);
				for(Permission p:permissionList){
					Map<String, Object> map = new HashMap<String, Object>();
					if(p.getFather() == null){
						//父菜单
						String childrenHal = "from Permission p where p.father.id="+p.getId()+"";
						List<Permission> childrenPermission = permissionDao.findByHql(childrenHal);
						if(childrenPermission != null && childrenPermission.size() != 0){
							//当父菜单有子菜单才展示
							map.put("id", p.getId());
							map.put("name", p.getName());
							map.put("pId", 0);
							map.put("open", false);
							treeList.add(map);
						}
					}else{
						//子菜单
						map.put("id", p.getId());
						map.put("name", p.getName());
						map.put("pId", p.getFather().getId());
						treeList.add(map);
					}
				}
			}else{
				//编辑角色，获取全部权限，并将拥有权限打钩
				String hasPermissionHql = "select p from Permission p,Roles r where r.id="+Integer.parseInt(rolesId)+" and p in elements(r.permission)";
				String noPermissionHql = "from Permission p where p.father.id is not null";
				String allFatherPermission = "from Permission p where p.father.id is null";

				List<Permission> noPermissionList = permissionDao.findByHql(noPermissionHql);                //查出所有权限，用于存放没有拥有的权限
				List<Permission> hasPermissionList = permissionDao.findByHql(hasPermissionHql);              //该用户拥有的权限
				List<Permission> noFatherPermissionList = permissionDao.findByHql(allFatherPermission);      //不拥有的父权限
				List<Permission> hasFatherPermissionList = new ArrayList<Permission>();                      //用来放用户拥有的权限的父权限
				
				//将已经拥有的权限移除
				noPermissionList.removeAll(hasPermissionList);   //该用户不拥有的权限
				
				Set<Integer> hasFatherIdSet = new HashSet<Integer>(); //用于存放拥有权限的父id
				
				//该角色拥有的权限
				for(Permission hasPermission:hasPermissionList){
					hasFatherIdSet.add(hasPermission.getFather().getId());
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", hasPermission.getId());
					map.put("name", hasPermission.getName());
					map.put("pId", hasPermission.getFather().getId());
					map.put("checked", true);
					
					treeList.add(map);
					treeName = treeName + hasPermission.getName() + ",";
					treeId = treeId + hasPermission.getId() + ",";
				}
				
				//该角色没有拥有的权限
				for(Permission noPermission:noPermissionList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", noPermission.getId());
					map.put("name", noPermission.getName());
					map.put("pId", noPermission.getFather().getId());
					
					treeList.add(map);
				}
				
				Iterator<Integer> hasFatherIter = hasFatherIdSet.iterator();
				while(hasFatherIter.hasNext()){
					Permission fatherPermission = permissionDao.findById(Permission.class, hasFatherIter.next());
					hasFatherPermissionList.add(fatherPermission);
				}
				
				noFatherPermissionList.removeAll(hasFatherPermissionList);
				
				for(Permission hasPermission:hasFatherPermissionList){
					String childrenHal = "from Permission p where p.father.id="+hasPermission.getId()+"";
					List<Permission> childrenPermission = permissionDao.findByHql(childrenHal);
					if(childrenPermission != null && childrenPermission.size() != 0){
						//当父菜单有子菜单才展示
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", hasPermission.getId());
						map.put("name", hasPermission.getName());
						map.put("pId", 0);
						map.put("checked", true);
						treeList.add(map);
					}
				}
				
				for(Permission noPermission:noFatherPermissionList){
					String childrenHal = "from Permission p where p.father.id="+noPermission.getId()+"";
					List<Permission> childrenPermission = permissionDao.findByHql(childrenHal);
					if(childrenPermission != null && childrenPermission.size() != 0){
						//当父菜单有子菜单才展示
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", noPermission.getId());
						map.put("name", noPermission.getName());
						map.put("pId", 0);
						treeList.add(map);
					}
				}
				
			}
		}
		
		dataMap.put("treeList", treeList);
		dataMap.put("treeName", treeName);
		dataMap.put("treeId", treeId);
		
		return dataMap;
	}

	/**
	 * 保存或者更新角色
	 */
	@Override
	public Object updateRoles(String type, String name, String father_id, String rolesId, String treeId) throws Exception {
		String result = "";
		if(!StringUtils.isBlank(type) && !StringUtils.isBlank(name) && !StringUtils.isBlank(rolesId)){
			if("0".equals(rolesId)){
				//新增
				if("1".equals(type)){
					//角色
					Roles roles = getUpdateRoles(name, father_id, treeId);
					rolesDao.save(roles);
					result = "1";
				}else if("2".equals(type)){
					//角色组
					Roles roles = new Roles(name);
					rolesDao.save(roles);
					result = "2";
				}
			}else{
				//修改
				if("1".equals(type)){
					//编辑角色
					Roles roles = getUpdateRoles(name, father_id, treeId);
					roles.setId(Integer.parseInt(rolesId));
					rolesDao.update(roles);
					result = "3";
				}else if("2".equals(type)){
					//编辑角色组
					Roles roles = new Roles(Integer.parseInt(rolesId), name);
					rolesDao.update(roles);
					result = "4";
				}
			}
		}
		return result;
	}

	/**
	 * 根据id查找角色
	 */
	@Override
	public Object findRolesById(String rolesId) throws Exception {
		Roles roles = null;
		if(!StringUtils.isBlank(rolesId) && StringUtils.isNumeric(rolesId)){
			roles = rolesDao.findById(Roles.class, Integer.parseInt(rolesId));
		}
		return roles;
	}
	
	/**
	 * 获得需要保存或者更新的角色
	 * @param name
	 * @param father_id
	 * @param rolesId
	 * @param treeId
	 * @return
	 */
	public Roles getUpdateRoles(String name, String father_id, String treeId){
		Roles fatherRoles = rolesDao.findById(Roles.class, Integer.parseInt(father_id));
		Roles roles = null;
		if(!StringUtils.isBlank(treeId)){
			//已分配权限
			Set<Permission> permissionSet = new HashSet<Permission>();
			String[] treeIds = treeId.split(",");
			for(int i=0;i<treeIds.length;i++){
				Permission permission = permissionDao.findById(Permission.class, Integer.parseInt(treeIds[i]));
				permissionSet.add(permission);
			}
			roles = new Roles(name, permissionSet, fatherRoles);
		}else{
			//未分配权限
			roles = new Roles(name, null, fatherRoles);
		}
		return roles;
	}

	/**
	 * 根据id删除角色
	 */
	@Override
	public Object deleteRolesById(String rolesId) throws Exception {
		if(!StringUtils.isBlank(rolesId) && StringUtils.isNumeric(rolesId)){
			Roles roles = rolesDao.findById(Roles.class, Integer.parseInt(rolesId));
			if(roles != null){
				rolesDao.delete(roles);
				return "1";
			}else{
				return "2";
			}
		}
		return "1";
	}

}
