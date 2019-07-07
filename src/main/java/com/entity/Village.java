package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Village{

	private String id;
	
	private String operationId;

	private String name;
	
	private String location;
	
	private String description;
	
	private String organizationId;
	
	private String organization;
	
	private Timestamp changeDate;

	private Timestamp createDate;
	
	private Timestamp updateDate;
	
	private boolean isDeleted;
	
	private List<Cultureaspect> cultureaspects;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Timestamp changeDate) {
		this.changeDate = changeDate;
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

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * @return the cultureaspects
	 */
	public List<Cultureaspect> getCultureaspects() {
		return cultureaspects;
	}

	/**
	 * @param cultureaspects the cultureaspects to set
	 */
	public void setCultureaspects(List<Cultureaspect> cultureaspects) {
		this.cultureaspects = cultureaspects;
	}

	
} 
