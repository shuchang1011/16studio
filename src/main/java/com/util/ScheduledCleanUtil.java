package com.util;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.entity.TempDoc;
import com.service.DocumentService;


@Component
@Lazy(false)
public class ScheduledCleanUtil {

	private static Logger logger = Logger.getLogger(ScheduledCleanUtil.class);
	
	private static final String TYPE_FILE = "file";
	
	private static final String TYPE_IMAGE = "image"; 
	
	private static final String TYPE_VIDEO = "video"; 
	
	private static String path = "c:/resource/static/";
	
	@Autowired@Qualifier("DocumentServiceImpl")
	private DocumentService documentService;
	
	@Scheduled(cron="10 0 0 * * ?")
	public void scheduledClean() {
		List<TempDoc> fileList = documentService.findTempDocByType(TYPE_FILE);
		List<TempDoc> imageList = documentService.findTempDocByType(TYPE_IMAGE);
		List<TempDoc> videoList = documentService.findTempDocByType(TYPE_VIDEO);
		String filePath = path+TYPE_FILE+"s/";
		String imagePath = path+TYPE_IMAGE+"s/";
		String videoPath = path+TYPE_VIDEO+"s/";
		File file=new File(filePath);
		File[] tempList = file.listFiles();
		clearDoc(fileList,tempList);
		file = new File(imagePath);
		tempList = file.listFiles();
		clearDoc(imageList, tempList);
		file = new File(videoPath);
		tempList = file.listFiles();
		clearDoc(videoList, tempList);
	}
	
	/**
	 * @param list
	 * @param tempList
	 * @method 获取对应存储相应文件的文件夹下的所有文件与数据库中的进行对比，冗余的则删除
	 */
	public void clearDoc(List<TempDoc> list,File[] tempList) {
		if(tempList!=null&&list!=null) {
			for(int i = 0;i < tempList.length;i++) {
				boolean flag = false;
				for(TempDoc tempDoc : list) {
					logger.info("tempDoc.getPath="+tempDoc.getPath());
					if(tempDoc.getPath().equals(tempList[i].getAbsolutePath().replaceAll("\\\\", "/"))) {
						flag = true;
						break;
					}
				}
				if(!flag&&tempList[i].exists()) {
					tempList[i].delete();
				}
			}
		}
	}
}
