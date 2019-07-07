package com.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MemberDao;
import com.entity.Member;
import com.service.MemberService;

@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService{

	@Autowired@Qualifier("MemberDao")
	private MemberDao memberDao;
	
	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void createMember(Member member) {
		// TODO Auto-generated method stub
		memberDao.createMember(member);
	}
	
	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void createRoleMember(String roleId, String memberId) {
		// TODO Auto-generated method stub
		memberDao.createRoleMember(roleId, memberId);
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void updateMember(Member member) {
		// TODO Auto-generated method stub
		memberDao.updateMember(member);
	}
	
	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void updateRoleMember(String roleId, String memberId) {
		// TODO Auto-generated method stub
		memberDao.updateRoleMember(roleId, memberId);
	}

	@Transactional(rollbackFor = {RuntimeException.class,Exception.class})
	@Override
	public void deleteMember(String memberId) {
		// TODO Auto-generated method stub
		memberDao.deleteMember(memberId);
		memberDao.deleteRoleMember(memberId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Member findOneByAccountId(String accountId) {
		// TODO Auto-generated method stub
		return memberDao.findOneByAccountId(accountId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public Member findOne(String memberId) {
		// TODO Auto-generated method stub
		return memberDao.findOne(memberId);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return memberDao.findAll();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Member> findAllByOrganizationId(String organizationId) {
		// TODO Auto-generated method stub
		return memberDao.findAllByOrganizationId(organizationId);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public List<Member> findFreeScholarByOrganizationId(String organizationId) {
		// TODO Auto-generated method stub
		return memberDao.findFreeScholarByOrganizationId(organizationId);
	}
	

	@Override
	public List<Member> findFreeInputorByOrganizationId(String organizationId) {
		// TODO Auto-generated method stub
		return memberDao.findFreeInputorByOrganizationId(organizationId);
	}

	@Override
	public List<Member> findScholarInTask(String taskId) {
		// TODO Ato-generated method stub
		List<String> list = memberDao.findScholarInTask(taskId);
		List<Member> memberList = new ArrayList<Member>();
		for (String id : list) {
			memberList.add(memberDao.findOne(id));
		}
		return memberList;
	}

}
