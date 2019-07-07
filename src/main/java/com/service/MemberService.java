package com.service;

import java.util.List;

import com.entity.Member;

public interface MemberService {

	public void createMember(Member member);
	
	public void createRoleMember(String roleId,String memberId);
	
	public void updateMember(Member member);
	
	public void updateRoleMember(String roleId,String memberId);
	
	public void deleteMember(String memberId);
	
	Member findOneByAccountId(String accountId);
	
	Member findOne(String memberId);
	
	List<Member> findAll();
	
	List<Member> findScholarInTask(String taskId);
	
	List<Member> findFreeScholarByOrganizationId(String organizationId);
	
	List<Member> findFreeInputorByOrganizationId(String organizationId);
	
	List<Member> findAllByOrganizationId(String organizationId);
	
}
