package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity.Member;

@Repository("MemberDao")
public interface MemberDao {
	
	public void createMember(Member member);
	
	public void createRoleMember(@Param("roleId")String roleId,@Param("memberId")String memberId);
	
	public void updateMember(Member member);
	
	public void updateRoleMember(@Param("roleId")String roleId,@Param("memberId")String memberId);
	
	public void deleteMember(@Param("memberId")String memberId);
	
	public void deleteRoleMember(@Param("memberId")String memberId);
	
	Member findOneByAccountId(@Param("accountId")String accountId);

	Member findOne(@Param("memberId")String memberId);
	
	List<String> findScholarInTask(@Param("taskId")String taskId);
	
	List<Member> findFreeScholarByOrganizationId(@Param("organizationId")String organizationId);
	
	List<Member> findFreeInputorByOrganizationId(@Param("organizationId")String organizationId);
	
	List<Member> findAll();
	
	List<Member> findAllByOrganizationId(@Param("organizationId")String organizationId);
	
}
