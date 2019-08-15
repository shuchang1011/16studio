package com.service.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.Constants;
import com.controller.DocumentController;
import com.dao.DocumentDao;
import com.entity.Document;
import com.entity.TempDoc;
import com.service.DocumentService;

@Service("DocumentServiceImpl")
public class DocumentServiceImpl implements DocumentService{

	private static Logger logger = Logger.getLogger(DocumentController.class);	
	
	@Autowired@Qualifier("DocumentDao")
	private DocumentDao documentDao;
	
	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void createTempDocument(TempDoc tempDoc) {
		// TODO Auto-generated method stub
		documentDao.createTempDocument(tempDoc);
	}

	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void updateDocument(Document document) {
		// TODO Auto-generated method stub
		documentDao.updateDocument(document);
	}

	@Transactional(rollbackFor={RuntimeException.class,Exception.class})
	@Override
	public void deleteDocument(String documentId) {
		// TODO Auto-generated method stub
		logger.info("deleterDocument");
		documentDao.deleteDocument(documentId);
		logger.info("success");
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Document findOne(String documentId) {
		// TODO Auto-generated method stub
		return documentDao.findOne(documentId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Document> findAll() {
		// TODO Auto-generated method stub
		return documentDao.findAll();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Document> findAllByVillageIdAndCultureaspectId(
			String villageId, String cultureaspectId) {
		// TODO Auto-generated method stub
		return documentDao.findAllByVillageIdAndCultureaspectId(villageId, cultureaspectId);
	}

	@Override
	public void createDocument(Document document) {
		// TODO Auto-generated method stub
		documentDao.createDocument(document);
	}

	@Override
	public List<TempDoc> findTempDoc(String tempDocId) {
		// TODO Auto-generated method stub
		return documentDao.findTempDoc(tempDocId);
	}

	@Override
	public void updateTempDoc(TempDoc tempDoc) {
		// TODO Auto-generated method stub
		documentDao.updateTempDoc(tempDoc);
	}

	@Override
	public List<TempDoc> findSubmitTempDoc(String auditMemberId) {
		// TODO Auto-generated method stub
		return documentDao.findSubmitTempDoc(auditMemberId);
	}

	@Override
	public void auditTempDoc(String tempDocId, String state, String html, String problem) {
		// TODO Auto-generated method stub
		if(state.equals(Constants.STATE_ARCHIVE)) {
			documentDao.tempDocPass(tempDocId,state,html);
		}else {
			documentDao.tempDocUnPass(tempDocId, state, problem);
		}
	}

	@Override
	public List<TempDoc> findPassedTempDoc(String fileMemberId) {
		// TODO Auto-generated method stub
		return documentDao.findPassedTempDoc(fileMemberId);
	}

	@Override
	public void archiveTempDoc(String tempDocId, String state) {
		// TODO Auto-generated method stub
		if(state.equals(Constants.STATE_ARCHIVE_SUCCESS)) {
			documentDao.archiveTempDoc(tempDocId, state);
			List<TempDoc> tempDoc = documentDao.findTempDoc(tempDocId);
			for (TempDoc tempDoc2 : tempDoc) {
				documentDao.archiveToDocument(tempDoc2);
			}
		}else {
			documentDao.unArchiveTempDoc(tempDocId, state);
		}
	}

	@Override
	public List<TempDoc> findTempDocByCreateMemberId(String createMemberId) {
		// TODO Auto-generated method stub
		return documentDao.findTempDocByCreateMemberId(createMemberId);
	}

	@Override
	public void submitTempDoc(String tempDocId,String content) {
		// TODO Auto-generated method stub
		documentDao.submitTempDoc(tempDocId,content);
	}

	@Override
	public void submitDoc(String tempDocId) {
		documentDao.submitDoc(tempDocId);
	}

	@Override
	public List<Document> findByVillage(String villageId) {
		// TODO Auto-generated method stub
		return documentDao.findByVillage(villageId);
	}

	@Override
	public List<Document> findByType(String type) {
		// TODO Auto-generated method stub
		return documentDao.findByType(type);
	}

	@Override
	public List<Document> findByTitle(String title) {
		// TODO Auto-generated method stub
		return documentDao.findByTitle(title);
	}

	@Override
	public List<TempDoc> findTempDocByType(String type) {
		// TODO Auto-generated method stub
		return documentDao.findTempDocByType(type);
	}

	@Override
	public void deleteTempDoc(String tempDocId) {
		// TODO Auto-generated method stub
		documentDao.deleteTempDoc(tempDocId);
	}

	@Override
	public TempDoc findTempDocById(String id) {
		// TODO Auto-generated method stub
		return documentDao.findTempDocById(id);
	}

	@Override
	public List<TempDoc> findTempDocByVillageIdAndCultureaspectId(
			String villageId, String cultureaspectId) {
		// TODO Auto-generated method stub
		return documentDao.findTempDocByVillageIdAndCultureaspectId(villageId, cultureaspectId);
	}

	@Override
	public List<TempDoc> findUnSubmitTempDoc(String createMemberId) {
		// TODO Auto-generated method stub
		return documentDao.findUnSubmitTempDoc(createMemberId);
	}

}
