package com.service;

import java.util.List;

import com.entity.Operation;
import com.entity.Village;

public interface HistoryService {

	public void createOperation(Operation operation);
	
	public void deleteOperation(String operationId);
	
	public void createVillageHistory(Village village);
	
	public void restoreVillage(Village village);
	
	public void recoveryVillage(String villageId);
	
	public void deleteVillage(String villageId);
	
	List<Operation> findAllOperation(String villageId,String target);
	
	Operation findOperation(String operationId);
	
	Village findVillageByOperationId(String operationId);
	
	
}
