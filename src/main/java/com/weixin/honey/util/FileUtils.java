package com.weixin.honey.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月25日下午4:13:35
 */
public class FileUtils {

	/**
	 * 文件上传
	 * @param file      文件
	 * @param realPath  上传路径
	 * @param picName   文件名称
	 */
	public static void uploadFile(MultipartFile file,String realPath,String picName){
		if(!(file.isEmpty())){
            //获取图片属性并保存
            File targetFile = new File(realPath,picName);
            try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
}
