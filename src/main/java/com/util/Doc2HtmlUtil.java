package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;

public class Doc2HtmlUtil {
	
	private static Doc2HtmlUtil doc2HtmlUtil; 
	/**
     * 获取Doc2HtmlUtil实例
     */ 
	public static synchronized Doc2HtmlUtil getDoc2HtmlUtilInstance() {
		if (doc2HtmlUtil == null) {
			doc2HtmlUtil = new Doc2HtmlUtil(); 
    	} 
    	return doc2HtmlUtil;
    } 

	/**
     * 转换文件成pdf
     * 
     * @param fromFileInputStream:
     * @throws IOException 
     */
    public static String file2swf(String fileName,String swfName,InputStream fromFileInputStream, String toFilePath,String type) throws Exception{
    	String docFileName = null;
    	String pdfFileName = null;
    	String swfFile = null;
    	if("doc".equals(type)||"docx".equals(type)){
            docFileName = fileName.concat(".doc");
            pdfFileName = fileName.concat(".pdf");
            swfFile = swfName.concat(".swf");
        }else if("xls".equals(type)||"xlsx".equals(type)){
            docFileName = fileName.concat(".xls");
            pdfFileName = fileName.concat(".pdf");
            swfFile = swfName.concat(".swf");
        }else if("ppt".equals(type)||"pptx".equals(type)){
            docFileName = fileName.concat(".ppt");
            pdfFileName = fileName.concat(".pdf");
            swfFile = swfName.concat(".swf");
        }else if("txt".equals(type)){
            docFileName = fileName.concat(".txt");
            pdfFileName = fileName.concat(".pdf");
            swfFile = swfName.concat(".swf");
            InputStreamReader in = new InputStreamReader(fromFileInputStream,"GB2312");
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(toFilePath+File.separatorChar+docFileName),"utf-8");
            char[] cbuf = new char[1024];   
            int n;   
            while((n=in.read(cbuf))!=-1){   
                out.write(cbuf, 0, n);  
                out.flush();
            } 
            System.out.println("data"+new String(cbuf));
            in.close();
            out.close();
            fromFileInputStream = new FileInputStream(toFilePath+File.separatorChar+docFileName);
        }else{
        	return null;
        }
    	File swfOutputFile = new File(toFilePath + File.separatorChar + swfFile);
        File pdfOutputFile = new File(toFilePath + File.separatorChar + pdfFileName);
        File docInputFile = new File(toFilePath + File.separatorChar + docFileName);
        System.out.println("pdfOutputFile:"+pdfOutputFile);
        if(!pdfOutputFile.exists()){
        	pdfOutputFile.getParentFile().mkdirs();
		 }
        if (pdfOutputFile.exists()){
        	pdfOutputFile.delete();
        }
        
        pdfOutputFile.createNewFile();
 
        docInputFile.createNewFile();
        
        /**
         * 由fromFileInputStream构建输入文件
         */
        int bytesRead = 0;
        byte[] buffer = new byte[1024 * 8];
    	OutputStream os = new FileOutputStream(docInputFile);
		while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
		    os.write(buffer, 0, bytesRead);
		}
		System.out.println("data3"+new String(buffer));
		os.close();
        fromFileInputStream.close();
        
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
		connection.connect();
        // convert
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, pdfOutputFile);
        connection.disconnect();
        // 转换完之后删除word文件
        //docInputFile.delete();
        
        Runtime r = Runtime.getRuntime();
        if (!swfOutputFile.exists()) {
        	 if (pdfOutputFile.exists()) {
        		 try {
                      Process p = r.exec("C:/swftools/pdf2swf.exe "+ pdfOutputFile.getPath() + " -o "+ swfOutputFile.getPath() + " -T 9");
                      System.err.println("****swf转换成功，文件输出： "+swfOutputFile.getPath() + "****");
                     if (pdfOutputFile.exists()){
                    	 //pdfOutputFile.delete();
                     }
                 } catch (IOException e) {
                     e.printStackTrace();
                     throw e;
                 }
        	 }
        } else {
            System.out.println("****pdf不存在,无法转换****");
        }
        return swfFile;
    }
    /**
     * 文件转换成Html
     * @param fromFileInputStream
     * @param toFilePath
     * @param type
     * @return
     * @throws Exception
     */
    public static String file2Html (String fileName,InputStream fromFileInputStream, String toFilePath,String type) throws Exception{
    	String docFileName = null;
    	String htmFileName = null;
    	System.out.println(fileName);
        if("doc".equals(type)||"docx".equals(type)){
            docFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".doc");
            htmFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".html");
        }else if("xls".equals(type)||"xlsx".equals(type)){
            docFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".xls");
            htmFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".html");
        }else if("ppt".equals(type)||"pptx".equals(type)){
            docFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".ppt");
            htmFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".html");
        }else if("txt".equals(type)){
            docFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".txt");
            htmFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".html");
        }else if("pdf".equals(type)){
        	docFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".pdf");
			htmFileName = fileName.substring(0, fileName.lastIndexOf(".")).concat(".html");
        }else{
        	return null;
        }
		File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        File docInputFile = new File(toFilePath + File.separatorChar + docFileName);
        System.out.println("htmlOutputFile:"+htmlOutputFile);
        System.out.println("docInputFile:"+docInputFile);
        if (htmlOutputFile.exists()){
        	htmlOutputFile.delete();
        }
		htmlOutputFile.createNewFile();
        docInputFile.createNewFile();
        System.out.println("htmFileName:"+htmFileName);
        /**
         * 由fromFileInputStream构建输入文件
         */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fromFileInputStream,"utf-8"));
        String doctext = null;
        String doc = null;
        while((doctext = bufferedReader.readLine())!=null) {
        	doc = doc + doctext + "\r\n";
        }
        byte[] buffer = doc.getBytes();
    	OutputStream os = new FileOutputStream(docInputFile);
		os.write(buffer);
		os.close();
        fromFileInputStream.close();
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
		connection.connect();
        // convert
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, htmlOutputFile);
        
        connection.disconnect();
        // 转换完之后删除word文件
        docInputFile.delete();
        //文件末尾写入script脚本跳出iframe框架
        String script = "<script type='text/javascript'>"
				+ "$(function(){"
				+ "alert('123');" 
				+ "if (top.location != self.location)top.location = self.location; "
				+ "})</script>";
		byte[] scriptByte = script.getBytes();
		//FileOutputStream(..,true)表示在文件末尾续写，FileOutputStream(..)会删除原有的文件中的内容
		os = new FileOutputStream(htmlOutputFile,true);
		os.write(scriptByte);
		os.close();
        System.out.println("htmFileName:"+htmFileName);
        return htmFileName;
	}	

}
