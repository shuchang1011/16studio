package com.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wordnik.swagger.annotations.Api;

import sun.misc.BASE64Decoder;

@Api(value="restful",description="文件上传、下载以及回显")
@Controller
@RequestMapping(value="/upload")
public class UploadImageController {
	
	/**
	 * 上传图片
	 * @param file
	 * @param request
	 * @param response
	 * @return MultipartFile uploadFile,
	 */
	@ResponseBody
	@RequestMapping(value="/images",method = RequestMethod.POST)
	public Map<String, Object> images (@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> params = new HashMap<String, Object>();
		if (file == null){
            System.out.println("未获得上传文件!");			
			return null;
		}
		try{
			 String filetype = request.getParameter("filetype")+"";
			 System.out.println("filetype:"+filetype);
			 String basePath = "";
			 if("image".equals(filetype)){
				 basePath = "c:/resource/static/images";
			 }else if("file".equals(filetype)){
				 basePath = "c:/resource/static/files";
			 }else if("video".equals(filetype)){
				 basePath = "c:/resource/static/videos";
			 }
			 if(basePath == null || "".equals(basePath)){
				 basePath = "c:/resource/static/videos";  //与properties文件中lyz.uploading.url相同，未读取到文件数据时为basePath赋默认值
			 }
			 System.out.println("filename:"+file.getOriginalFilename());
			 String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			 System.out.println("ext = "+ext);
			 String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")).concat("_").concat(String.valueOf((int)(Math.random() * 1000+1000))).concat(ext);
			 StringBuilder sb = new StringBuilder();
			 //拼接保存路径
			 sb.append(basePath).append("/").append(fileName);
			 
			 String visitUrl = basePath.concat("/"+fileName);
			 File f = new File(sb.toString());
			 if(!f.exists()){
				 f.getParentFile().mkdirs();
			 }else {
				 fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")).concat("_").concat(String.valueOf((int)(Math.random() * 1000+1000))).concat("1").concat(ext);
				 sb = new StringBuilder();
				 sb.append(basePath).append("/").append(fileName);
				 visitUrl =  basePath.concat("/"+fileName);
				 f = new File(sb.toString());
				 f.getParentFile().mkdirs();
			 }
			 OutputStream out = new FileOutputStream(f);
			 FileCopyUtils.copy(file.getInputStream(), out);
			 out.close();
			 params.put("state", "SUCCESS");
			 params.put("url", visitUrl);
			 params.put("size", file.getSize());
			 params.put("original", fileName);
			 params.put("type", file.getContentType());
			 params.put("filename", file.getOriginalFilename());
			 System.out.println("url:"+visitUrl+" original:"+fileName+" filename:"+file.getOriginalFilename()+" type:"+file.getContentType());
		} catch (Exception e){
			 params.put("state", "ERROR");
			 e.printStackTrace();
		}
		 return params;
	}
	
	/**
	 * 供读取服务器上传成功的图片显示到jsp上使用(多个图片循环调用)
	 * @param request
	 * @param response
	 * @param imagePath  图片地址
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/viewImagesToPage")
	public String viewImagesToPage(HttpServletRequest request,HttpServletResponse response,
				@RequestParam(value = "imagePath", required = false) String imagePath) {
		try {
			imagePath = new String(imagePath.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("imagePath:"+imagePath);
		ServletOutputStream out = null;
		FileInputStream ips = null;
		try {
			ips = new FileInputStream(new File(imagePath));
			response.setContentType("multipart/form-data");
			out = response.getOutputStream();
			// 读取文件流
			int i = 0;
			byte[] buffer = new byte[4096];
			while ((i = ips.read(buffer)) != -1) {
				// 写文件流
				out.write(buffer, 0, i);
			}
			out.flush();
			ips.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (ips != null) {
				try {
					ips.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/fileDownLoad",method = RequestMethod.GET)
	public void fileDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 下载本地文件
		String url = request.getParameter("url")+"";
		String fileName = request.getParameter("filename")+"";
		//如果是IE浏览器，则用URLEncode解析
		String agent=request.getHeader("User-Agent").toLowerCase();
		if(agent.indexOf("mise")>0){
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}else{//如果是谷歌、火狐则解析为ISO-8859-1
			fileName = new String(fileName.getBytes("ISO8859-1"), "utf-8");
		}
		System.out.println("filename:"+fileName+" url:"+url);
		// 读到流中 
		InputStream inStream = new FileInputStream(url);// 文件的存放路径
//		fileName = url.substring(url.lastIndexOf("/")+1);
//		System.out.println("filename:"+fileName);
		// 设置输出的格式
		response.reset();
//		response.setContentType("bin");
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 涂鸦上传
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/scrawlUpload",method = RequestMethod.POST)
	public Map<String, Object> scrawlUpload(HttpServletResponse response,  
            HttpServletRequest request)throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		String filetype = request.getParameter("filetype");
		System.out.println("filetype:"+filetype);
		String upfile = request.getParameter("upfile");//Base64编码过的图片数据信息，对字节数组字符串进行Base64解码 
		System.out.println("upfile:"+upfile);
		String basePath = "";
		 if("scrawl".equals(filetype)){
			 basePath = "c:/resource/static/scrawl";
		 }
		 if(basePath == null || "".equals(basePath)){
			 basePath = "c:/resource/static";  
		 }
		String fileName = String.valueOf(System.currentTimeMillis()).concat("_").concat(String.valueOf(Math.random() * 1000)+1000).concat(".").concat("png");
		StringBuilder sb = new StringBuilder();
		 //拼接保存路径
		sb.append(basePath).append("/").append(fileName);
		// 在自己的项目中构造出一个用于存放用户照片的文件夹
		File f = new File(sb.toString());
		if (!f.exists()) {
			f.getParentFile().mkdirs();
		}
		// 使用BASE64对图片文件数据进行解码操作
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// 通过Base64解密，将图片数据解密成字节数组
			byte[] bytes = decoder.decodeBuffer(upfile);
			// 构造字节数组输入流
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			// 读取输入流的数据
			BufferedImage bi = ImageIO.read(bais);
			// 将数据信息写进图片文件中
			ImageIO.write(bi, "png", f);// 不管输出什么格式图片，此处不需改动
			params.put("state", "SUCCESS");
			params.put("url", sb.toString());
			params.put("size", upfile.length());
			params.put("original", fileName);
			params.put("type", "png");
			bais.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
		return params;
	}
	
}