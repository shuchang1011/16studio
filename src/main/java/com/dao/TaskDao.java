package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Task;

@Repository("TaskDao")
public interface TaskDao {

	public void createTask(Task task);
	
	public void createTaskMember(@Param("taskId")String taskId,@Param("memberId")String memberId);
	
	public void updateTask(Task task);
	
	public void FinishTask(@Param("taskId")String taskId);
	
/*	public void submitTask(@Param("taskId")String taskId);
	
	public void updateUnpassed(@Param("taskId")String taskId);*/
	
	public void deleteTask(@Param("taskId")String taskId);
	
	public void deleteTaskMember(@Param("taskId")String taskId);
	
	public List<String> findMemberInTask(@Param("taskId")String taskId);
	
	Task findOne(@Param("taskId")String taskId);
	
	List<Task> findTaskByVillage(@Param("villageId")String villageId);
	
	Task findTaskByVillageAndCultureaspect(@Param("villageId")String villageId,@Param("cultureaspectId")String cultureaspectId);
	
	List<Task> findAllByOrganizationId(@Param("organizationId")String OrganizationId);
	
	List<Task> findAllByMemberId(@Param("memberId")String memberId);
	
	List<Task> findAll();
	
/*	List<Task> findSubmitTask(@Param("organizationId")String organizationId);
	
	List<Task> findUnSubmitTask(@Param("memberId")String memberId);*/
	
	List<Task> findFinishedTask(@Param("organizationId")String organizationId);
	
	List<Task> findUnFinishedTask(@Param("organizationId")String organizationId);
	
}
