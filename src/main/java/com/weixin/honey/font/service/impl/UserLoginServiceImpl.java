package com.weixin.honey.font.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.honey.dao.BaseDao;
import com.weixin.honey.font.service.UserLoginService;
import com.weixin.honey.pojo.User;
import com.weixin.honey.util.AESOperator;

/**
 * 前端用户登录或注册实现类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年5月22日上午10:43:09
 */
@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {
	
	private static final Logger logger = Logger.getLogger(UserLoginServiceImpl.class);
	
	@Autowired
	private BaseDao<User, Serializable> userDao;
	
	@Value("${salt}")
	private String salt;
	
	@Value("${userSession}")
	private String userSession;
	
	/**
	 * 用户登录
	 */
	@Override
	public Object loginInGirl(HttpServletRequest request,String username, String password) throws Exception {
		String result = "";
		if(!StringUtils.isBlank(username) && !StringUtils.isBlank(password)){
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				subject.login(token);
			} catch (UnknownAccountException e) {
				//用户名不存在
				logger.info("用户名("+username+")不存在");
				return "2";
			} catch (IncorrectCredentialsException e){
				//密码错误
				logger.info("用户名("+username+")密码("+password+")错误");
				return "3";
			}
			
			//将对象放入到session
			request.getSession().setAttribute(userSession, subject.getPrincipal());
			result = "4";
		}else{
			logger.info("账号或密码为空，不能登录");
			result = "1";
		}
		return result;
	}

	/**
	 * 用户注册
	 */
	@Override
	public Object registerIn(HttpServletRequest request,String username, String password) throws Exception {
		String result = "";
		if(!StringUtils.isBlank(username) && !StringUtils.isBlank(password)){
			if(username.length() <= 5 || password.length() <= 5){
				result = "4";
			}else{
				String isHaveHql = "from User u where u.username='"+username+"'";
				List<User> isHaveUserList = userDao.findByHql(isHaveHql);
				if(isHaveUserList == null || isHaveUserList.size() == 0){
					//该用户名没有被占用，可以注册
					password = AESOperator.getInstance().encrypt(password+salt);       // 密码加密
					User user = new User(username, password, new Date());
					userDao.save(user);
					result = "3";
					request.getSession().setAttribute(userSession, user);
					logger.info("用户("+username+")注册成功");
				}else{
					//该用户名已经被占用，不能注册
					result = "2";
					logger.info("该用户名("+username+")已经被占用，不能注册");
				}
			}
		}else{
			logger.info("账号或密码为空，不能注册");
			result = "1";
		}
		return result;
	}

}
