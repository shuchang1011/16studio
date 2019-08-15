package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Constants;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import org.apache.log4j.Logger;

public class Doc2HtmlUtil {

    private static final Logger logger = Logger.getLogger(Doc2HtmlUtil.class);

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
        if("doc".equals(type)||"docx".equals(type)){
            docFileName = fileName.concat(".doc");
            htmFileName = fileName.concat(".html");
        }else if("xls".equals(type)||"xlsx".equals(type)){
            docFileName = fileName.concat(".xls");
            htmFileName = fileName.concat(".html");
        }else if("ppt".equals(type)||"pptx".equals(type)){
            docFileName = fileName.concat(".ppt");
            htmFileName = fileName.concat(".html");
        }else if("txt".equals(type)){
            docFileName = fileName.concat(".txt");
            htmFileName = fileName.concat(".html");
        }else if("pdf".equals(type)){
            docFileName = fileName.concat(".pdf");
            htmFileName = fileName.concat(".html");
        }else{
        	return null;
        }
		File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        File docInputFile = new File("C:/static/file/" + File.separatorChar + docFileName);
        if (!htmlOutputFile.exists()){
        	htmlOutputFile.getParentFile().mkdirs();
		}
     if (htmlOutputFile.exists()){
        htmlOutputFile.delete();
     }
     htmlOutputFile.createNewFile();
     docInputFile.createNewFile();
     System.out.println("htmFileName:"+htmFileName);
        /**
         * 由fromFileInputStream构建输入文件
         */
        int bytesRead = 0;
        byte[] buffer = new byte[1024 * 8];
        OutputStream os = new FileOutputStream(docInputFile);
        while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        fromFileInputStream.close();
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
		connection.connect();
        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, htmlOutputFile);
        
        connection.disconnect();
        // 转换完之后删除word文件
        docInputFile.delete();
        System.out.println("htmFileName:"+htmFileName);
        return htmFileName;
	}	

    public static String html2String(String htmlFile) {
    	StringBuffer sb = new StringBuffer();
    	try {
    		InputStream is = new FileInputStream(htmlFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");

			} // 记得关闭流数据 节约内存消耗 is.close();
            is.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	logger.info("sb.toString()="+sb.toString());
    	return clearFormat(sb.toString().replaceFirst("text-align: justify;", ""));
    }

    /**
     * 清除一些不需要的html标记
     *
     * @param htmlStr
     *                带有复杂html标记的html语句
     * @return 去除了不需要html标记的语句
     */
    protected static String clearFormat(String htmlStr) {
        // 获取body内容的正则
        String bodyReg = "<BODY .*</BODY>";
        Pattern bodyPattern = Pattern.compile(bodyReg);
        Matcher bodyMatcher = bodyPattern.matcher(htmlStr);
        if (bodyMatcher.find()) {
            // 获取BODY内容，并转化BODY标签为DIV
            htmlStr = bodyMatcher.group().replaceFirst("<BODY", "<DIV")
                    .replaceAll("</BODY>", "</DIV>");
        }
        // 调整图片地址
        htmlStr = htmlStr.replaceAll("<IMG SRC=\"", "<IMG SRC=\""+ Constants.STATICRESOUCESURL+"/html/");
        // 把<P></P>转换成</div></div>保留样式
        // content = content.replaceAll("(<P)([^>]*>.*?)(<\\/P>)",
        // "<div$2</div>");
        // 把<P></P>转换成</div></div>并删除样式
        htmlStr = htmlStr.replaceAll("(<P)([^>]*)(>.*?)(<\\/P>)", "<p$3</p>");
        // 删除不需要的标签
        htmlStr = htmlStr
                .replaceAll(
                        "<[/]?(font|FONT|span|SPAN|xml|XML|del|DEL|ins|INS|meta|META|[ovwxpOVWXP]:\\w+)[^>]*?>",
                        "");
        // 删除不需要的属性
        htmlStr = htmlStr
                .replaceAll(
                        "<([^>]*)(?:lang|LANG|class|CLASS|style|STYLE|size|SIZE|face|FACE|[ovwxpOVWXP]:\\w+)=(?:'[^']*'|\"\"[^\"\"]*\"\"|[^>]+)([^>]*)>",
                        "<$1$2>");
        return htmlStr;
    }

}
