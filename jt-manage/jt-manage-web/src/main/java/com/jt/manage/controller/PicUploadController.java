package com.jt.manage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller  
public class PicUploadController {

	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult upload(MultipartFile uploadFile){
		PicUploadResult result=new PicUploadResult();
		String fileName=uploadFile.getOriginalFilename();
		String extName=fileName.substring(fileName.lastIndexOf("."));   //扩展名
		
		if(fileName.matches("^.+\\.(jpg|gif|jpeg|png)$")){
			//判断是否为木马文件
			try{
				BufferedImage buf=ImageIO.read(uploadFile.getInputStream());
				result.setWidth(buf.getWidth()+"");
				result.setHeight(buf.getHeight()+"");
				
				//生成绝对路径和相对路径
				String dir = "c:\\jt-upload/";    //两种斜杠都可以
				String path="images/"+ new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/";
				String newFileName=System.currentTimeMillis()+ "" +RandomUtils.nextInt(100, 999)+extName;
				
				//设置相对路径
				result.setUrl("http://image.jt.com/" + path + newFileName);
				
				//产生目录,加下划线表示临时
				File _dir = new File(dir+path);
				if(!_dir.exists()){
					_dir.mkdirs();   //创建多级目录
				}
				//设置绝对路径并储存，最后一个/后面开始是文件名
				uploadFile.transferTo(new File(dir+path+newFileName));
				result.setError(0);
				
			}catch(Exception e){
				e.printStackTrace();
				result.setError(1);
				
			}
			
		}else{
			result.setError(1);
		}
		
		return result;
	};
	
	
	
}
