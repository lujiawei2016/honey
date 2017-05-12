package com.weixin.honey.shiro.realm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.pojo.Permission;
import com.weixin.honey.pojo.Roles;
import com.weixin.honey.pojo.User;
import com.weixin.honey.util.AESOperator;
import com.weixin.honey.util.EhcacheUtils;


/**
 * 自定义Realm
 * @author lujiawei
 * 2015年12月21日14:12:49
 *
 */
public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private BaseDao<User, Serializable> userDao;
	
	@Autowired
	private BaseDao<Roles, Serializable> rolesDao;
	
	@Autowired
	private BaseDao<Permission, Serializable> permissionDao;
	
	@Autowired
	private EhcacheUtils ehcacheUtils;
	
	@Value("${salt}")
	private String salt;
	
	@Value("${permissionCache}")
	private String permissionCache;
	
	/**
	 * 用于认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		//从token取出用户的身份信息（token有用户输入的用户名和密码）
		String username = (String) token.getPrincipal();//取出用户名
		
		User user = userDao.findObjByProperty("username", username, User.class);
		
		if(user == null){
			throw new UnknownAccountException("用户名不存在");
		}
		
		String password = (AESOperator.getInstance().decrypt(user.getPassword())).replaceAll(salt, "");
		
		//如果查询到返回认证信息AuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, "ShiroRealm");
		
		return simpleAuthenticationInfo;
	}

	/**
	 * 用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		User user = (User) principals.getPrimaryPrincipal();         //拿到身份
		List<String> permissions = new ArrayList<String>();  //用来存放用户拥有的权限
		
		Object permissionCacheObj = ehcacheUtils.getCache("permissionCache", permissionCache+"_"+user.getId());
		if(permissionCacheObj == null){
			String rolesHql = "select r from User u,Roles r where u.id="+user.getId()+" and u in elements(r.user)";
			List<Roles> rolesList = rolesDao.findByHql(rolesHql);
			for(Roles roles:rolesList){
				String permissionHql = "select p from Permission p,Roles r where r.id="+roles.getId()+" and p in elements(r.permission)";
				List<Permission> permissionList = permissionDao.findByHql(permissionHql);
				for(Permission p:permissionList){
					permissions.add(p.getMark());
				}
			}
			ehcacheUtils.setCache("permissionCache", permissionCache+"_"+user.getId(), permissions);
		}else{
			permissions = (List<String>) permissionCacheObj;
		}
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将授权信息放入到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
		
	}
	
}
