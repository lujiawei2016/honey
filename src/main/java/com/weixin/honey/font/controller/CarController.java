package com.weixin.honey.font.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.honey.font.service.CarService;
import com.weixin.honey.pojo.Car;
import com.weixin.honey.pojo.Girl;
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
	
	private static final Logger logger = Logger.getLogger(CarController.class);
	
	@Autowired
	private CarService carService;
	
	@Value("user")
	private String userSession;
	
	@RequestMapping(value="/listCar")
	public String listCar(HttpServletRequest request,ModelMap modelMap){
		User user = (User) request.getSession().getAttribute(userSession);
		List<Girl> carList = (List<Girl>) carService.listCar(user.getId(), 0, 9);
		if(carList == null || carList.size() == 0){
			logger.info("第一次获取备战区妹纸失败，再次获取....");
			carList = (List<Girl>) carService.listCar(user.getId(), 0, 9);
		}
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
	@ResponseBody
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
