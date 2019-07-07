package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Document;
import com.entity.TempDoc;

public interface DocumentService {

	public void createDocument(Document document);
	
	public void createTempDocument(TempDoc tempDoc);
	
	public void updateDocument(Document document);
	
	public void updateTempDoc(TempDoc tempDoc);
	
	public void deleteDocument(String documentId);
	
	public void submitTempDoc(String tempDocId);
	
	public void auditTempDoc(String tempDocId,String state);
	
	public void archiveTempDoc(String tempDocId,String state);
	
	public void deleteTempDoc(String tempDocId);
	
	Document findOne(String documentId);
	
	TempDoc findTempDocById(String id);
	
	List<TempDoc> findTempDocByCreateMemberId(String createMemberId);
	
	List<TempDoc> findUnSubmitTempDoc(String createMemberId);
	
	List<TempDoc> findSubmitTempDoc(String auditMemberId);
	
	List<TempDoc> findPassedTempDoc(String fileMemberId);
	
	List<TempDoc> findTempDoc(String tempDocId);
	
	List<TempDoc> findTempDocByType(String type);
	
	List<TempDoc> findTempDocByVillageIdAndCultureaspectId(String villageId,String cultureaspectId);
	
	List<Document> findAll();
	
	List<Document> findByVillage(String villageId);
	
	List<Document> findByType(String type);
	
	List<Document> findByTitle(String title);
	
	List<Document> findAllByVillageIdAndCultureaspectId(String villageId,String cultureaspectId);

}
