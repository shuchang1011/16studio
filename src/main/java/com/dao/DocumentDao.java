package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Document;
import com.entity.TempDoc;

@Repository("DocumentDao")
public interface DocumentDao {

	public void createDocument(Document document);
	
	public void createTempDocument(TempDoc tempDoc);
	
	public void updateDocument(Document document);
	
	public void updateTempDoc(TempDoc tempDoc);
	
	public void deleteTempDoc(@Param("tempDocId")String tempDocId);
	
	public void deleteDocument(@Param("documentId")String documentId);
	
	public void submitTempDoc(@Param("tempDocId")String tempDocId);
	
	public void tempDocPass(@Param("tempDocId")String tempDocId,@Param("state")String state);
	
	public void tempDocUnPass(@Param("tempDocId")String tempDocId,@Param("state")String state);
	
	public void archiveToDocument(TempDoc tempDoc);
	
	public void archiveTempDoc(@Param("tempDocId")String tempDocId,@Param("state")String state);
	
	public void unArchiveTempDoc(@Param("tempDocId")String tempDocId,@Param("state")String state);
	
	List<TempDoc> findTempDocByCreateMemberId(@Param("createMemberId")String createMemberId);
	
	TempDoc findTempDocById(@Param("id")String id);
	
	List<TempDoc> findUnSubmitTempDoc(@Param("createMemberId")String createMemberId);
	
	List<TempDoc> findSubmitTempDoc(@Param("auditMemberId")String auditMemberId);
	
	List<TempDoc> findPassedTempDoc(@Param("fileMemberId")String fileMemberId);
	
	Document findOne(@Param("documentId")String documentId);
	
	List<TempDoc> findTempDoc(@Param("tempDocId")String tempDocId);
	
	List<TempDoc> findTempDocByType(@Param("type")String type);
	
	List<TempDoc> findTempDocByVillageIdAndCultureaspectId(@Param("villageId")String villageId,@Param("cultureaspectId")String cultureaspectId);

	List<Document> findAll();
	
	List<Document> findByVillage(@Param("villageId")String villageId);
	
	List<Document> findByType(@Param("type")String type);
	
	List<Document> findByTitle(@Param("title")String title);
	
	List<Document> findAllByVillageIdAndCultureaspectId(@Param("villageId")String villageId,@Param("cultureaspectId")String cultureaspectId);
	
}
