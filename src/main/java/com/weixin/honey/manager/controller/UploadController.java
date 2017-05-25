package com.weixin.honey.manager.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.weixin.honey.util.FileUtils;

/**
 * 文件上传controller
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月26日下午1:16:14
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

	/**
	 * 文件上传
	 * @param request
	 * @param response
	 * @param file
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadFile")
	@RequiresPermissions("upload:uploadFile")
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request,HttpServletResponse response,
			@RequestParam MultipartFile[] file,ModelMap modelMap) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String imgUrl = "resource/uploadFile/";
        String picName = sdf.format(new Date())+(UUID.randomUUID().toString().substring(0, 4)).toUpperCase();
        String realPath = request.getSession().getServletContext().getRealPath(""+File.separator+"resource"+File.separator+"uploadFile");
        
        //循环文件，可能不止一个文件
        for(MultipartFile f:file){
        	String suffix = f.getOriginalFilename().substring((f.getOriginalFilename().indexOf(".")));  //后缀
        	picName = picName + suffix;
        	
        	FileUtils.uploadFile(f, realPath, picName);
        	
        	imgUrl = imgUrl + picName;
        	
        	map.put("picName", picName);
        	map.put("imgUrl", imgUrl);
        }
		return map;
	}
	
}
