package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Operation;
import com.entity.Village;

@Repository("HistoryDao")
public interface HistoryDao {
	
	public void createOperation(Operation operation);
	
	public void deleteOperation(@Param("operationId")String operationId);
	
	public void createVillageHistory(Village village);
	
	public void restoreVillage(Village village);
	
	public void recoveryVillage(@Param("villageId")String villageId);
	
	public void deleteVillage(@Param("operationId")String operationId);
	
	List<Operation> findAllOperation(@Param("villageId")String villageId,@Param("target")String target);
	
	Operation findOperation(@Param("operationId")String operationId);
	
	Village findVillageByOperationId(@Param("operationId")String operationId);
	

}