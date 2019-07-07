package com.service.serviceImpl;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TaskDao;
import com.entity.Member;
import com.entity.Task;
import com.service.TaskService;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskService{
	
	private static Logger logger = Logger.getLogger(TaskServiceImpl.class);

	@Autowired@Qualifier("TaskDao")
	private TaskDao taskDao;
	
	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void createTask(Task task,String[] memberId) {
		// TODO Auto-generated method stub
		taskDao.createTask(task);
		for (String id : memberId) {
			logger.info("memberId = {}"+id);
			taskDao.createTaskMember(task.getId(), id);
		}
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void updateTask(Task task) {
		// TODO Auto-generated method stub
		taskDao.updateTask(task);
	}
	
	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void FinishTask(String taskId) {
		// TODO Auto-generated method stub
		taskDao.FinishTask(taskId);
		taskDao.deleteTaskMember(taskId);
	}
	
	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void deleteTask(String taskId) {
		// TODO Auto-generated method stub
		taskDao.deleteTask(taskId);
		taskDao.deleteTaskMember(taskId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Task findOne(String taskId) {
		// TODO Auto-generated method stub
		return taskDao.findOne(taskId);
	}

	@Override
	public boolean IsMemberInTask(String villageId,String cultureaspectId, String memberId) {
		// TODO Auto-generated method stub
		Task task = taskDao.findTaskByVillageAndCultureaspect(villageId,cultureaspectId);
		if(task == null) {
			return false;
		}else {
			for(Member member : task.getMember()) {
				if(memberId.equals(member.getId()))
					return true;
			}
		}
		return false;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Task> findAllByMemberId(String memberId) {
		// TODO Auto-generated method stub
		return taskDao.findAllByMemberId(memberId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Task> findAll() {
		// TODO Auto-generated method stub
		return taskDao.findAll();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Task> findFinishedTask(String organizationId) {
		// TODO Auto-generated method stub
		return taskDao.findFinishedTask(organizationId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Task> findUnFinishedTask(String organizationId) {
		// TODO Auto-generated method stub
		return taskDao.findUnFinishedTask(organizationId);
	}

	@Override
	public Task findTaskByVillageAndCultureaspect(String villageId,String cultureaspectId) {
		// TODO Auto-generated method stub
		return taskDao.findTaskByVillageAndCultureaspect(villageId,cultureaspectId);
	}

	@Override
	public List<Task> findAllByOrganizationId(String OrganizationId) {
		// TODO Auto-generated method stub
		return taskDao.findAllByOrganizationId(OrganizationId);
	}

	@Override
	public List<Task> findTaskByVillage(String villageId) {
		// TODO Auto-generated method stub
		return taskDao.findTaskByVillage(villageId);
	}

}
