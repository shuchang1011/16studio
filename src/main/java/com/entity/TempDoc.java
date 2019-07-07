package com.entity;

import java.sql.Timestamp;

public class TempDoc {

	private String id;
	
	private String fileId;

	private String villageId;
	
	private String village;

	private String cultureaspectId;
	
	private String cultureaspect;

	private String createMemberId;
	
	private String createMember;
	
	private String auditMemberId;
	
	private String auditMember;

	private String fileMemberId;

	private String title;

	private String description;
	
	private String type;

	private String format;

	private String path;

	private String content;
	
	private String state;
	
	private String problem;
	
	private boolean isSubmited;
	
	private boolean isApproved;
	
	private boolean isArchived;
	
	private Timestamp createDate;
	
	private Timestamp submitDate;
	
	private Timestamp auditDate;

	private Timestamp fileDate;
	
	private Timestamp updateDate;
	
	private boolean isDeleted;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getCultureaspectId() {
		return cultureaspectId;
	}

	public void setCultureaspectId(String cultureaspectId) {
		this.cultureaspectId = cultureaspectId;
	}

	public String getCreateMemberId() {
		return createMemberId;
	}

	public void setCreateMemberId(String createMemberId) {
		this.createMemberId = createMemberId;
	}

	public String getAuditMemberId() {
		return auditMemberId;
	}

	public void setAuditMemberId(String auditMemberId) {
		this.auditMemberId = auditMemberId;
	}

	public String getFileMemberId() {
		return fileMemberId;
	}

	public void setFileMemberId(String fileMemberId) {
		this.fileMemberId = fileMemberId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public Timestamp getFileDate() {
		return fileDate;
	}

	public void setFileDate(Timestamp fileDate) {
		this.fileDate = fileDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Timestamp getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Timestamp submitDate) {
		this.submitDate = submitDate;
	}

	public boolean isIsSubmited() {
		return isSubmited;
	}

	public void setIsSubmited(boolean isSubmited) {
		this.isSubmited = isSubmited;
	}

	public String getCultureaspect() {
		return cultureaspect;
	}

	public void setCultureaspect(String cultureaspect) {
		this.cultureaspect = cultureaspect;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getCreateMember() {
		return createMember;
	}

	public void setCreateMember(String createMember) {
		this.createMember = createMember;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the auditMember
	 */
	public String getAuditMember() {
		return auditMember;
	}

	/**
	 * @param auditMember the auditMember to set
	 */
	public void setAuditMember(String auditMember) {
		this.auditMember = auditMember;
	}

	/**
	 * @return the isArchived
	 */
	public boolean isArchived() {
		return isArchived;
	}

	/**
	 * @param isArchived the isArchived to set
	 */
	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
