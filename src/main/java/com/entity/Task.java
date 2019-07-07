package com.entity;

import java.sql.Timestamp;
import java.util.List;
import com.entity.Member;
import com.entity.Village;

public class Task {

	private String id;
	
	private String organizationId;
	
	private String villageId;
	
	private String cultureaspectId;
	
	private String createMemberId;
	
	private String title;
	
	private String content;
	
	private String fileType;

	private boolean isFinished;
	
	private Timestamp finishedDate;
	
	private Timestamp createDate;
	
	private Timestamp updateDate;
	
	private boolean isDeleted;
	
	private List<Member> member;
	
	private Village village;

	public List<Member> getMember() {
		return member;
	}

	public void setMember(List<Member> member) {
		this.member = member;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Timestamp finishedDate) {
		this.finishedDate = finishedDate;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public String getCreateMemberId() {
		return createMemberId;
	}

	public void setCreateMemberId(String createMemberId) {
		this.createMemberId = createMemberId;
	}

	/**
	 * @return the cultureaspectId
	 */
	public String getCultureaspectId() {
		return cultureaspectId;
	}

	/**
	 * @param cultureaspectId the cultureaspectId to set
	 */
	public void setCultureaspectId(String cultureaspectId) {
		this.cultureaspectId = cultureaspectId;
	}

}
