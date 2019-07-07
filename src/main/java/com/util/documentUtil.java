package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class documentUtil {

	public String uploadFile(MultipartFile file,String fileType) {
		String path=null;
		if (file == null){
            System.out.println("未获得上传文件!");			
		}
		try{
			 String basePath = "";
			 if("image".equals(fileType)){
				 basePath = "d:/resource/static/images";
			 }else if("file".equals(fileType)){
				 basePath = "d:/resource/static/files";
			 }else if("video".equals(fileType)){
				 basePath = "d:/resource/static/videos";
			 }
			 if(basePath == null || "".equals(basePath)){
				 basePath = "d:/resources/static/videos";  //与properties文件中lyz.uploading.url相同，未读取到文件数据时为basePath赋默认值
			 }
			 System.out.println("filename:"+file.getOriginalFilename());
			 String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			 String fileName = String.valueOf(System.currentTimeMillis()).concat("_").concat(String.valueOf(Math.random() * 1000)+1000).concat(".").concat(ext);
			 StringBuilder sb = new StringBuilder();
			 //拼接保存路径
			 sb.append(basePath).append("/").append(fileName);
			 path = sb.toString();
			 
			 File f = new File(sb.toString());
			 if(!f.exists()){
				 f.getParentFile().mkdirs();
			 }
			 OutputStream out = new FileOutputStream(f);
			 FileCopyUtils.copy(file.getInputStream(), out);
		}catch (Exception e){
			 e.printStackTrace();
		}
		return path;
		
	}
	
}
