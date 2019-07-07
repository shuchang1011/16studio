package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Task;

public interface TaskService {

	public void createTask(Task task,String[] memberId);
	
	public void updateTask(Task task);
	
/*	public void submitTask(String taskId);
	
	public void updateUnpassed(String taskId);*/
	
	public void FinishTask(String taskId);
	
	public void deleteTask(String taskId);
	
	Task findOne(String taskId);
	
	Task findTaskByVillageAndCultureaspect(String villageId,String cultureaspectId);
	
	List<Task> findTaskByVillage(String villageId);
	
	public boolean IsMemberInTask(String villageId,String cultureaspectId,String memberId);
	
	List<Task> findAllByOrganizationId(String OrganizationId);
	
	List<Task> findAllByMemberId(String memberId);
	
	List<Task> findAll();

/*	List<Task> findSubmitTask(String organizationId);
	
	List<Task> findUnSubmitTask(String memberId);*/
	
	List<Task> findFinishedTask(String organizationId);
	
	List<Task> findUnFinishedTask(String organizationId);
	
}
