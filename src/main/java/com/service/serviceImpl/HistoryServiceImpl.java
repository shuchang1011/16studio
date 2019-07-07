package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.HistoryDao;
import com.entity.Operation;
import com.entity.Village;
import com.service.HistoryService;

@Service("HistoryServiceImpl")
public class HistoryServiceImpl implements HistoryService{

	@Autowired@Qualifier("HistoryDao")
	private HistoryDao historyDao;
	
	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void createOperation(Operation operation) {
		// TODO Auto-generated method stub
		historyDao.createOperation(operation);
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void deleteOperation(String operationId) {
		// TODO Auto-generated method stub
		historyDao.deleteOperation(operationId);
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void createVillageHistory(Village village) {
		// TODO Auto-generated method stub
		historyDao.createVillageHistory(village);
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void restoreVillage(Village village) {
		// TODO Auto-generated method stub
		historyDao.restoreVillage(village);
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void recoveryVillage(String villageId) {
		// TODO Auto-generated method stub
		historyDao.recoveryVillage(villageId);
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void deleteVillage(String villageId) {
		// TODO Auto-generated method stub
		historyDao.deleteVillage(villageId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Operation> findAllOperation(String villageId, String target) {
		// TODO Auto-generated method stub
		return historyDao.findAllOperation(villageId, target);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Village findVillageByOperationId(String operationId) {
		// TODO Auto-generated method stub
		return historyDao.findVillageByOperationId(operationId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Operation findOperation(String operationId) {
		// TODO Auto-generated method stub
		return historyDao.findOperation(operationId);
	}

}
