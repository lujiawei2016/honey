package com.weixin.honey.font.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.honey.font.service.CarService;
import com.weixin.honey.pojo.User;

/**
 * 备战区controller
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月21日下午1:07:34
 */
@Controller
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Value("user")
	private String userSession;
	
	@RequestMapping(value="/listCar")
	public String listCar(HttpServletRequest request,ModelMap modelMap){
		User user = (User) request.getSession().getAttribute(userSession);
		Object carList = carService.listCar(user.getId(), 0, 10);
		modelMap.put("carList", carList);
		return "front/car";
	}
	
	/**
	 * 加入备战区
	 * @param girlId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addCar")
	public Object addCar(String girlId,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute(userSession);
		Object result = null;
		try {
			result = carService.addCar(user.getId(), girlId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
