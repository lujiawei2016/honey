package com.weixin.honey.manager.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.manager.service.PermissionService;
import com.weixin.honey.pojo.Permission;

/**
 * 权限实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月17日下午5:30:55
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private BaseDao<Permission, Serializable> permissionDao;
	
	@Value("${pageLength}")
	private int pageLength;  //默认一页展示pageLength条数据
	
	/**
	 * 分页查找权限
	 */
	@Override
	public Object findPagePermission(String name, String pageNum, String length) {
		int pNum = 1;             //默认从第一页开始查找数据
		int lenNum = pageLength;  //默认查找pageLength条数据
		
		if(pageNum != null){pNum = Integer.parseInt(pageNum);}
		if(length != null){lenNum = Integer.parseInt(length);}
		
		pNum = (pNum-1)*lenNum;
		name = name==null ? "" : name;
		
		String hql = "from Permission p where p.name like '%"+name+"%'";
		Map<String, Object> map = permissionDao.pageResult(hql, pNum, lenNum);
		
		return map;
	}

	/**
	 * 查找父级权限
	 */
	@Override
	public Object findFatherPermission() throws Exception {
		String hql = "from Permission p where p.father.id is null";
		List<Permission> fatherPermissionList = permissionDao.findByHql(hql);
		return fatherPermissionList;
	}

	/**
	 * 保存或更新权限
	 */
	@Override
	public Object updatePermission(String type, String father_id, Permission permission) throws Exception {
		String result = "";
		if(!StringUtils.isBlank(type) && !StringUtils.isBlank(permission.getName())){
			if("1".equals(type)){
				//权限
				if(!StringUtils.isBlank(permission.getMark()) && !StringUtils.isBlank(father_id)){
					Permission fatherPermission = permissionDao.findById(Permission.class, Integer.parseInt(father_id));
					if(fatherPermission != null){
						Permission newPermission = null;
						if(permission.getId() == 0){
							//新增权限
							Permission markPermission = permissionDao.findObjByProperty("mark", permission.getMark(), Permission.class);
							if(markPermission == null){
								//权限标识不能相同
								newPermission = new Permission(permission.getName(), permission.getMark(), fatherPermission);
								result = "5";
								permissionDao.save(newPermission);
							}else{
								result = "6";
							}
						}else{
							//修改权限
							String hql = "from Permission p where p.mark='"+permission.getMark()+"' and p.id <> "+permission.getId()+"";
							List<Permission> newPermissionList = permissionDao.findByHql(hql);
							if(newPermissionList == null || newPermissionList.size() == 0){
								newPermission = new Permission(permission.getId(), permission.getName(), permission.getMark(), fatherPermission);
								result = "4";
								permissionDao.save(newPermission);
							}else{
								result = "6";
							}
						}
					}
				}else{
					//权限标识mark为空
					result = "3";
				}
			}else if("2".equals(type)){
				//权限组
				if(permission.getId() == 0){
					//新增权限组
					Permission permissionGroup = new Permission(permission.getName(), "fatherMark");
					permissionDao.save(permissionGroup);
					result = "1";
				}else{
					//修改权限组
					Permission permissionGroup = new Permission(permission.getId(),permission.getName(), "fatherMark");
					permissionDao.update(permissionGroup);
					result = "2";
				}
			}
		}
		return result;
	}

	/**
	 * 根据id删除权限
	 */
	@Override
	public Object deletePermissionById(String id) throws Exception {
		String result = "0";
		if(!StringUtils.isBlank(id) && StringUtils.isNumeric(id)){
			Permission permission = permissionDao.findById(Permission.class, Integer.parseInt(id));
			if(permission != null){
				//进行删除
				permissionDao.delete(permission);
				result = "1";
			}else{
				result = "2";
			}
		}
		return result;
	}

}
