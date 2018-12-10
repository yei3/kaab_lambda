package com.yei3.oox.kaab_inventarios.database.entity;

import java.sql.Timestamp;

public class Department implements Entity{
	private int id;
	private int companyID;
	private String departmentType;
	private int departmentID;
	private String name;
	private String headDepartment;
	private int statusID;
	private Timestamp creationDateTime;
	private Timestamp lastModDateTime;
	private Timestamp deleteDateTime;
	private int creationUserID;
	private int lastModUserID;
	private int deleteUserID;
	
	private String TABLE = "DEPARTMENTS";
	
	private String[] columns = {
			"id",
			"companyID",
			"departmentType",
			"departmentID",
			"name",
			"headDepartment",
			"statusID",
			"creationDateTime",
			"lastModDateTime",
			"deleteDateTime",
			"creationUserID",
			"lastModUserID",
			"deleteUserID"
			};
	
	private Class[] types = {
			int.class,
			int.class,
			String.class,
			int.class,
			String.class,
			String.class,
			int.class,
			Timestamp.class,
			Timestamp.class,
			Timestamp.class,
			int.class,
			int.class,
			int.class
			};
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", companyID=" + companyID + ", departmentType=" + departmentType
				+ ", departmentID=" + departmentID + ", name=" + name + ", headDepartment=" + headDepartment
				+ ", statusID=" + statusID + ", creationDateTime=" + creationDateTime + ", lastModDateTime="
				+ lastModDateTime + ", deleteDateTime=" + deleteDateTime + ", creationUserID=" + creationUserID
				+ ", lastModUserID=" + lastModUserID + ", deleteUserID=" + deleteUserID + "]";
	}
	
	public Department(int companyID, String departmentType, int departmentID, String name, String headDepartment,
			int statusID) {
		this.companyID = companyID;
		this.departmentType = departmentType;
		this.departmentID = departmentID;
		this.name = name;
		this.headDepartment = headDepartment;
		this.statusID = statusID;
	}
	
	public Department() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public String getDepartmentType() {
		return departmentType;
	}
	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadDepartment() {
		return headDepartment;
	}
	public void setHeadDepartment(String headDepartment) {
		this.headDepartment = headDepartment;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public Timestamp getLastModDateTime() {
		return lastModDateTime;
	}
	public void setLastModDateTime(Timestamp lastModDateTime) {
		this.lastModDateTime = lastModDateTime;
	}
	public Timestamp getDeleteDateTime() {
		return deleteDateTime;
	}
	public void setDeleteDateTime(Timestamp deleteDateTime) {
		this.deleteDateTime = deleteDateTime;
	}
	public int getCreationUserID() {
		return creationUserID;
	}
	public void setCreationUserID(int creationUserID) {
		this.creationUserID = creationUserID;
	}
	public int getLastModUserID() {
		return lastModUserID;
	}
	public void setLastModUserID(int lastModUserID) {
		this.lastModUserID = lastModUserID;
	}
	public int getDeleteUserID() {
		return deleteUserID;
	}
	public void setDeleteUserID(int deleteUserID) {
		this.deleteUserID = deleteUserID;
	}
	public String getTable() {
		return this.TABLE;
	}
	public String[] getColumns() {
		return this.columns;
	}
	public Class[] getTypes() {
		return this.types;
	}
}
